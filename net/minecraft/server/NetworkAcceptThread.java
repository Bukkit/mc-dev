package net.minecraft.server;

import java.io.IOException;
import java.net.Socket;

class NetworkAcceptThread extends Thread {

    final MinecraftServer a;

    final NetworkListenThread b;

    NetworkAcceptThread(NetworkListenThread networklistenthread, String s, MinecraftServer minecraftserver) {
        super(s);
        this.b = networklistenthread;
        this.a = minecraftserver;
    }

    public void run() {
        while (this.b.b) {
            try {
                Socket socket = NetworkListenThread.a(this.b).accept();

                if (socket != null) {
                    NetLoginHandler netloginhandler = new NetLoginHandler(this.a, socket, "Connection #" + NetworkListenThread.b(this.b));

                    NetworkListenThread.a(this.b, netloginhandler);
                }
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }
}

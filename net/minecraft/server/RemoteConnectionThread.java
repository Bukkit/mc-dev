package net.minecraft.server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class RemoteConnectionThread implements Runnable {

    protected boolean running = false;
    protected IMinecraftServer server;
    protected Thread thread;
    protected int d = 5;
    protected List e = new ArrayList();
    protected List f = new ArrayList();

    RemoteConnectionThread(IMinecraftServer iminecraftserver) {
        this.server = iminecraftserver;
        if (this.server.isDebugging()) {
            this.warning("Debugging is enabled, performance maybe reduced!");
        }
    }

    public synchronized void a() {
        this.thread = new Thread(this);
        this.thread.start();
        this.running = true;
    }

    public boolean b() {
        return this.running;
    }

    protected void debug(String s) {
        this.server.debug(s);
    }

    protected void info(String s) {
        this.server.sendMessage(s);
    }

    protected void warning(String s) {
        this.server.warning(s);
    }

    protected void error(String s) {
        this.server.severe(s);
    }

    protected int c() {
        return this.server.getPlayerCount();
    }

    protected void a(DatagramSocket datagramsocket) {
        this.debug("registerSocket: " + datagramsocket);
        this.e.add(datagramsocket);
    }

    protected boolean a(DatagramSocket datagramsocket, boolean flag) {
        this.debug("closeSocket: " + datagramsocket);
        if (null == datagramsocket) {
            return false;
        } else {
            boolean flag1 = false;

            if (!datagramsocket.isClosed()) {
                datagramsocket.close();
                flag1 = true;
            }

            if (flag) {
                this.e.remove(datagramsocket);
            }

            return flag1;
        }
    }

    protected boolean a(ServerSocket serversocket) {
        return this.a(serversocket, true);
    }

    protected boolean a(ServerSocket serversocket, boolean flag) {
        this.debug("closeSocket: " + serversocket);
        if (null == serversocket) {
            return false;
        } else {
            boolean flag1 = false;

            try {
                if (!serversocket.isClosed()) {
                    serversocket.close();
                    flag1 = true;
                }
            } catch (IOException ioexception) {
                this.warning("IO: " + ioexception.getMessage());
            }

            if (flag) {
                this.f.remove(serversocket);
            }

            return flag1;
        }
    }

    protected void d() {
        this.a(false);
    }

    protected void a(boolean flag) {
        int i = 0;
        Iterator iterator = this.e.iterator();

        while (iterator.hasNext()) {
            DatagramSocket datagramsocket = (DatagramSocket) iterator.next();

            if (this.a(datagramsocket, false)) {
                ++i;
            }
        }

        this.e.clear();
        iterator = this.f.iterator();

        while (iterator.hasNext()) {
            ServerSocket serversocket = (ServerSocket) iterator.next();

            if (this.a(serversocket, false)) {
                ++i;
            }
        }

        this.f.clear();
        if (flag && 0 < i) {
            this.warning("Force closed " + i + " sockets");
        }
    }
}

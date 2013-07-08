package net.minecraft.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DedicatedServerConnectionThread extends Thread {

    private final List a = Collections.synchronizedList(new ArrayList());
    private final HashMap b = new HashMap();
    private int c;
    private final ServerSocket d;
    private ServerConnection e;
    private final InetAddress f;
    private final int g;

    public DedicatedServerConnectionThread(ServerConnection serverconnection, InetAddress inetaddress, int i) {
        super("Listen thread");
        this.e = serverconnection;
        this.g = i;
        this.d = new ServerSocket(i, 0, inetaddress);
        this.f = inetaddress == null ? this.d.getInetAddress() : inetaddress;
        this.d.setPerformancePreferences(0, 2, 1);
    }

    public void a() {
        List list = this.a;

        synchronized (this.a) {
            for (int i = 0; i < this.a.size(); ++i) {
                PendingConnection pendingconnection = (PendingConnection) this.a.get(i);

                try {
                    pendingconnection.d();
                } catch (Exception exception) {
                    pendingconnection.disconnect("Internal server error");
                    this.e.d().getLogger().warning("Failed to handle packet for " + pendingconnection.getName() + ": " + exception, (Throwable) exception);
                }

                if (pendingconnection.b) {
                    this.a.remove(i--);
                }

                pendingconnection.networkManager.a();
            }
        }
    }

    public void run() {
        while (this.e.a) {
            try {
                Socket socket = this.d.accept();
                PendingConnection pendingconnection = new PendingConnection(this.e.d(), socket, "Connection #" + this.c++);

                this.a(pendingconnection);
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }

        this.e.d().getLogger().info("Closing listening thread");
    }

    private void a(PendingConnection pendingconnection) {
        if (pendingconnection == null) {
            throw new IllegalArgumentException("Got null pendingconnection!");
        } else {
            List list = this.a;

            synchronized (this.a) {
                this.a.add(pendingconnection);
            }
        }
    }

    public void a(InetAddress inetaddress) {
        if (inetaddress != null) {
            HashMap hashmap = this.b;

            synchronized (this.b) {
                this.b.remove(inetaddress);
            }
        }
    }

    public void b() {
        try {
            this.d.close();
        } catch (Throwable throwable) {
            ;
        }
    }
}

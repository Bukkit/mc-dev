package net.minecraft.server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class RemoteConnectionThread implements Runnable {

    private static final AtomicInteger h = new AtomicInteger(0);
    protected boolean running;
    protected IMinecraftServer server;
    protected final String c;
    protected Thread thread;
    protected int e = 5;
    protected List f = new ArrayList();
    protected List g = new ArrayList();

    protected RemoteConnectionThread(IMinecraftServer iminecraftserver, String s) {
        this.server = iminecraftserver;
        this.c = s;
        if (this.server.isDebugging()) {
            this.warning("Debugging is enabled, performance maybe reduced!");
        }
    }

    public synchronized void a() {
        this.thread = new Thread(this, this.c + " #" + h.incrementAndGet());
        this.thread.start();
        this.running = true;
    }

    public boolean c() {
        return this.running;
    }

    protected void debug(String s) {
        this.server.i(s);
    }

    protected void info(String s) {
        this.server.info(s);
    }

    protected void warning(String s) {
        this.server.warning(s);
    }

    protected void error(String s) {
        this.server.h(s);
    }

    protected int d() {
        return this.server.B();
    }

    protected void a(DatagramSocket datagramsocket) {
        this.debug("registerSocket: " + datagramsocket);
        this.f.add(datagramsocket);
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
                this.f.remove(datagramsocket);
            }

            return flag1;
        }
    }

    protected boolean b(ServerSocket serversocket) {
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
                this.g.remove(serversocket);
            }

            return flag1;
        }
    }

    protected void e() {
        this.a(false);
    }

    protected void a(boolean flag) {
        int i = 0;
        Iterator iterator = this.f.iterator();

        while (iterator.hasNext()) {
            DatagramSocket datagramsocket = (DatagramSocket) iterator.next();

            if (this.a(datagramsocket, false)) {
                ++i;
            }
        }

        this.f.clear();
        iterator = this.g.iterator();

        while (iterator.hasNext()) {
            ServerSocket serversocket = (ServerSocket) iterator.next();

            if (this.a(serversocket, false)) {
                ++i;
            }
        }

        this.g.clear();
        if (flag && 0 < i) {
            this.warning("Force closed " + i + " sockets");
        }
    }
}

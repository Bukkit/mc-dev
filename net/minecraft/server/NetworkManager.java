package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NetworkManager {

    public static final Object a = new Object();
    public static int b;
    public static int c;
    private Object d = new Object();
    private Socket e;
    private final SocketAddress f;
    private DataInputStream g;
    private DataOutputStream h;
    private boolean i = true;
    private List j = Collections.synchronizedList(new ArrayList());
    private List k = Collections.synchronizedList(new ArrayList());
    private List l = Collections.synchronizedList(new ArrayList());
    private NetHandler m;
    private boolean n = false;
    private Thread o;
    private Thread p;
    private boolean q = false;
    private String r = "";
    private int s = 0;
    private int t = 0;
    private int u = 0;

    public NetworkManager(Socket socket, String s, NetHandler nethandler) {
        this.e = socket;
        this.f = socket.getRemoteSocketAddress();
        this.m = nethandler;
        socket.setTrafficClass(24);
        this.g = new DataInputStream(socket.getInputStream());
        this.h = new DataOutputStream(socket.getOutputStream());
        this.p = new NetworkReaderThread(this, s + " read thread");
        this.o = new NetworkWriterThread(this, s + " write thread");
        this.p.start();
        this.o.start();
    }

    public void a(NetHandler nethandler) {
        this.m = nethandler;
    }

    public void a(Packet packet) {
        if (!this.n) {
            Object object = this.d;

            synchronized (this.d) {
                this.t += packet.a() + 1;
                if (packet.j) {
                    this.l.add(packet);
                } else {
                    this.k.add(packet);
                }
            }
        }
    }

    private void e() {
        try {
            boolean flag = true;
            Object object;
            Packet packet;

            if (!this.k.isEmpty()) {
                flag = false;
                object = this.d;
                synchronized (this.d) {
                    packet = (Packet) this.k.remove(0);
                    this.t -= packet.a() + 1;
                }

                Packet.a(packet, this.h);
            }

            if ((flag || this.u-- <= 0) && !this.l.isEmpty()) {
                flag = false;
                object = this.d;
                synchronized (this.d) {
                    packet = (Packet) this.l.remove(0);
                    this.t -= packet.a() + 1;
                }

                Packet.a(packet, this.h);
                this.u = 50;
            }

            if (flag) {
                Thread.sleep(10L);
            }
        } catch (InterruptedException interruptedexception) {
            ;
        } catch (Exception exception) {
            if (!this.q) {
                this.a(exception);
            }
        }
    }

    private void f() {
        try {
            Packet packet = Packet.b(this.g);

            if (packet != null) {
                this.j.add(packet);
            } else {
                this.a("End of stream");
            }
        } catch (Exception exception) {
            if (!this.q) {
                this.a(exception);
            }
        }
    }

    private void a(Exception exception) {
        exception.printStackTrace();
        this.a("Internal exception: " + exception.toString());
    }

    public void a(String s) {
        if (this.i) {
            this.q = true;
            this.r = s;
            (new NetworkMasterThread(this)).start();
            this.i = false;

            try {
                this.g.close();
                this.g = null;
            } catch (Throwable throwable) {
                ;
            }

            try {
                this.h.close();
                this.h = null;
            } catch (Throwable throwable1) {
                ;
            }

            try {
                this.e.close();
                this.e = null;
            } catch (Throwable throwable2) {
                ;
            }
        }
    }

    public void a() {
        if (this.t > 1048576) {
            this.a("Send buffer overflow");
        }

        if (this.j.isEmpty()) {
            if (this.s++ == 1200) {
                this.a("Timed out");
            }
        } else {
            this.s = 0;
        }

        int i = 100;

        while (!this.j.isEmpty() && i-- >= 0) {
            Packet packet = (Packet) this.j.remove(0);

            packet.a(this.m);
        }

        if (this.q && this.j.isEmpty()) {
            this.m.a(this.r);
        }
    }

    public SocketAddress b() {
        return this.f;
    }

    public void c() {
        this.n = true;
        this.p.interrupt();
        (new ThreadMonitorConnection(this)).start();
    }

    public int d() {
        return this.l.size();
    }

    static boolean a(NetworkManager networkmanager) {
        return networkmanager.i;
    }

    static boolean b(NetworkManager networkmanager) {
        return networkmanager.n;
    }

    static void c(NetworkManager networkmanager) {
        networkmanager.f();
    }

    static void d(NetworkManager networkmanager) {
        networkmanager.e();
    }

    static Thread e(NetworkManager networkmanager) {
        return networkmanager.p;
    }

    static Thread f(NetworkManager networkmanager) {
        return networkmanager.o;
    }
}

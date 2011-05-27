package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NetworkManager {

    public static final Object a = new Object();
    public static int b;
    public static int c;
    private Object d = new Object();
    private Socket e;
    private DataInputStream f;
    private DataOutputStream g;
    private boolean h = true;
    private List i = Collections.synchronizedList(new LinkedList());
    private List j = Collections.synchronizedList(new LinkedList());
    private List k = Collections.synchronizedList(new LinkedList());
    private NetHandler l;
    private boolean m = false;
    private Thread n;
    private Thread o;
    private boolean p = false;
    private String q = "";
    private int r = 0;
    private int s = 0;
    private int t = 0;

    public NetworkManager(Socket socket, String s, NetHandler nethandler) {
        this.e = socket;
        this.l = nethandler;
        socket.setTrafficClass(24);
        this.f = new DataInputStream(socket.getInputStream());
        this.g = new DataOutputStream(socket.getOutputStream());
        this.o = new NetworkReaderThread(this, s + " read thread");
        this.n = new NetworkWriterThread(this, s + " write thread");
        this.o.start();
        this.n.start();
    }

    public void a(NetHandler nethandler) {
        this.l = nethandler;
    }

    public void a(Packet packet) {
        if (!this.m) {
            Object object = this.d;

            synchronized (this.d) {
                this.s += packet.a() + 1;
                if (packet.j) {
                    this.k.add(packet);
                } else {
                    this.j.add(packet);
                }
            }
        }
    }

    private void e() {
        try {
            boolean flag = true;
            Object object;
            Packet packet;

            if (!this.j.isEmpty()) {
                flag = false;
                object = this.d;
                synchronized (this.d) {
                    packet = (Packet) this.j.remove(0);
                    this.s -= packet.a() + 1;
                }

                Packet.a(packet, this.g);
            }

            if ((flag || this.t-- <= 0) && !this.k.isEmpty()) {
                flag = false;
                object = this.d;
                synchronized (this.d) {
                    packet = (Packet) this.k.remove(0);
                    this.s -= packet.a() + 1;
                }

                Packet.a(packet, this.g);
                this.t = 50;
            }

            if (flag) {
                Thread.sleep(10L);
            }
        } catch (InterruptedException interruptedexception) {
            ;
        } catch (Exception exception) {
            if (!this.p) {
                this.a(exception);
            }
        }
    }

    private void f() {
        try {
            Packet packet = Packet.b(this.f);

            if (packet != null) {
                this.i.add(packet);
            } else {
                this.a("End of stream");
            }
        } catch (Exception exception) {
            if (!this.p) {
                this.a(exception);
            }
        }
    }

    private void a(Exception exception) {
        exception.printStackTrace();
        this.a("Internal exception: " + exception.toString());
    }

    public void a(String s) {
        if (this.h) {
            this.p = true;
            this.q = s;
            (new NetworkMasterThread(this)).start();
            this.h = false;

            try {
                this.f.close();
                this.f = null;
            } catch (Throwable throwable) {
                ;
            }

            try {
                this.g.close();
                this.g = null;
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
        if (this.s > 1048576) {
            this.a("Send buffer overflow");
        }

        if (this.i.isEmpty()) {
            if (this.r++ == 1200) {
                this.a("Timed out");
            }
        } else {
            this.r = 0;
        }

        int i = 100;

        while (!this.i.isEmpty() && i-- >= 0) {
            Packet packet = (Packet) this.i.remove(0);

            packet.a(this.l);
        }

        if (this.p && this.i.isEmpty()) {
            this.l.a(this.q);
        }
    }

    public SocketAddress b() {
        return this.e.getRemoteSocketAddress();
    }

    public void c() {
        this.m = true;
        this.o.interrupt();
        (new ThreadMonitorConnection(this)).start();
    }

    public int d() {
        return this.k.size();
    }

    static boolean a(NetworkManager networkmanager) {
        return networkmanager.h;
    }

    static boolean b(NetworkManager networkmanager) {
        return networkmanager.m;
    }

    static void c(NetworkManager networkmanager) {
        networkmanager.f();
    }

    static void d(NetworkManager networkmanager) {
        networkmanager.e();
    }

    static Thread e(NetworkManager networkmanager) {
        return networkmanager.o;
    }

    static Thread f(NetworkManager networkmanager) {
        return networkmanager.n;
    }
}

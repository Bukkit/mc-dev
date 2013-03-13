package net.minecraft.server;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;

public class MemoryNetworkManager implements INetworkManager {

    private static final SocketAddress a = new InetSocketAddress("127.0.0.1", 0);
    private final List b;
    private final IConsoleLogManager c;
    private MemoryNetworkManager d;
    private Connection e;
    private boolean f;
    private String g;
    private Object[] h;

    public void a(Connection connection) {
        this.e = connection;
    }

    public void queue(Packet packet) {
        if (!this.f) {
            this.d.b(packet);
        }
    }

    public void a() {}

    public void b() {
        int i = 2500;

        while (i-- >= 0 && !this.b.isEmpty()) {
            Packet packet = (Packet) this.b.remove(0);

            packet.handle(this.e);
        }

        if (this.b.size() > i) {
            this.c.warning("Memory connection overburdened; after processing 2500 packets, we still have " + this.b.size() + " to go!");
        }

        if (this.f && this.b.isEmpty()) {
            this.e.a(this.g, this.h);
        }
    }

    public SocketAddress getSocketAddress() {
        return a;
    }

    public void d() {
        this.f = true;
    }

    public void a(String s, Object... aobject) {
        this.f = true;
        this.g = s;
        this.h = aobject;
    }

    public int e() {
        return 0;
    }

    public void b(Packet packet) {
        if (packet.a_() && this.e.b()) {
            packet.handle(this.e);
        } else {
            this.b.add(packet);
        }
    }
}

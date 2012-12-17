package net.minecraft.server;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryNetworkManager implements INetworkManager {

    private static final SocketAddress a = new InetSocketAddress("127.0.0.1", 0);
    private final List b = Collections.synchronizedList(new ArrayList());
    private MemoryNetworkManager c;
    private Connection d;
    private boolean e = false;
    private String f = "";
    private Object[] g;
    private boolean h = false;

    public MemoryNetworkManager(Connection connection) {
        this.d = connection;
    }

    public void a(Connection connection) {
        this.d = connection;
    }

    public void queue(Packet packet) {
        if (!this.e) {
            this.c.b(packet);
        }
    }

    public void a() {}

    public void b() {
        int i = 2500;

        while (i-- >= 0 && !this.b.isEmpty()) {
            Packet packet = (Packet) this.b.remove(0);

            packet.handle(this.d);
        }

        if (this.b.size() > i) {
            System.out.println("Memory connection overburdened; after processing 2500 packets, we still have " + this.b.size() + " to go!");
        }

        if (this.e && this.b.isEmpty()) {
            this.d.a(this.f, this.g);
        }
    }

    public SocketAddress getSocketAddress() {
        return a;
    }

    public void d() {
        this.e = true;
    }

    public void a(String s, Object... aobject) {
        this.e = true;
        this.f = s;
        this.g = aobject;
    }

    public int e() {
        return 0;
    }

    public void b(Packet packet) {
        String s = this.d.a() ? ">" : "<";

        if (packet.a_() && this.d.b()) {
            packet.handle(this.d);
        } else {
            this.b.add(packet);
        }
    }
}

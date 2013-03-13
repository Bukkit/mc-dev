package net.minecraft.server;

import java.net.InetAddress;

public class DedicatedServerConnection extends ServerConnection {

    private final DedicatedServerConnectionThread b;

    public DedicatedServerConnection(MinecraftServer minecraftserver, InetAddress inetaddress, int i) {
        super(minecraftserver);
        this.b = new DedicatedServerConnectionThread(this, inetaddress, i);
        this.b.start();
    }

    public void a() {
        super.a();
        this.b.b();
        this.b.interrupt();
    }

    public void b() {
        this.b.a();
        super.b();
    }

    public DedicatedServer c() {
        return (DedicatedServer) super.d();
    }

    public void a(InetAddress inetaddress) {
        this.b.a(inetaddress);
    }

    public MinecraftServer d() {
        return this.c();
    }
}

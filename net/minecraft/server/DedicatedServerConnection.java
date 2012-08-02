package net.minecraft.server;

import java.net.InetAddress;

public class DedicatedServerConnection extends ServerConnection {

    private final DedicatedServerConnectionThread c;

    public DedicatedServerConnection(MinecraftServer minecraftserver, InetAddress inetaddress, int i) {
        super(minecraftserver);
        this.c = new DedicatedServerConnectionThread(this, inetaddress, i);
        this.c.start();
    }

    public void a() {
        super.a();
        this.c.b();
        this.c.interrupt();
    }

    public void b() {
        this.c.a();
        super.b();
    }

    public DedicatedServer c() {
        return (DedicatedServer) super.d();
    }

    public void a(InetAddress inetaddress) {
        this.c.a(inetaddress);
    }

    public MinecraftServer d() {
        return this.c();
    }
}

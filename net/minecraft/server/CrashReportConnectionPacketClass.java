package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportConnectionPacketClass implements Callable {

    final Packet a;
    final PlayerConnection b;

    CrashReportConnectionPacketClass(PlayerConnection playerconnection, Packet packet) {
        this.b = playerconnection;
        this.a = packet;
    }

    public String a() {
        return this.a.getClass().getCanonicalName();
    }

    public Object call() {
        return this.a();
    }
}

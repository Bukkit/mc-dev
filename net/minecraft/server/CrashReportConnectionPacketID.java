package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportConnectionPacketID implements Callable {

    final Packet a;

    final PlayerConnection b;

    CrashReportConnectionPacketID(PlayerConnection playerconnection, Packet packet) {
        this.b = playerconnection;
        this.a = packet;
    }

    public String a() {
        return String.valueOf(this.a.n());
    }

    public Object call() {
        return this.a();
    }
}

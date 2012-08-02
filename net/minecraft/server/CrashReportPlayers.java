package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportPlayers implements Callable {

    final World a;

    CrashReportPlayers(World world) {
        this.a = world;
    }

    public String a() {
        return this.a.players.size() + " total; " + this.a.players.toString();
    }

    public Object call() {
        return this.a();
    }
}

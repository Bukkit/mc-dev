package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportChunkStats implements Callable {

    final World a;

    CrashReportChunkStats(World world) {
        this.a = world;
    }

    public String a() {
        return this.a.chunkProvider.getName();
    }

    public Object call() {
        return this.a();
    }
}

package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportLevelTime implements Callable {

    final WorldData a;

    CrashReportLevelTime(WorldData worlddata) {
        this.a = worlddata;
    }

    public String a() {
        return String.format("%d game time, %d day time", new Object[] { Long.valueOf(WorldData.g(this.a)), Long.valueOf(WorldData.h(this.a))});
    }

    public Object call() {
        return this.a();
    }
}

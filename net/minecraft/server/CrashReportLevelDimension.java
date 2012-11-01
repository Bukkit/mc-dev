package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportLevelDimension implements Callable {

    final WorldData a;

    CrashReportLevelDimension(WorldData worlddata) {
        this.a = worlddata;
    }

    public String a() {
        return String.valueOf(WorldData.i(this.a));
    }

    public Object call() {
        return this.a();
    }
}

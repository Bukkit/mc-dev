package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportLevelGeneratorOptions implements Callable {

    final WorldData a;

    CrashReportLevelGeneratorOptions(WorldData worlddata) {
        this.a = worlddata;
    }

    public String a() {
        return WorldData.c(this.a);
    }

    public Object call() {
        return this.a();
    }
}

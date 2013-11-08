package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportLevelGenerator implements Callable {

    final WorldData a;

    CrashReportLevelGenerator(WorldData worlddata) {
        this.a = worlddata;
    }

    public String a() {
        return String.format("ID %02d - %s, ver %d. Features enabled: %b", new Object[] { Integer.valueOf(WorldData.a(this.a).g()), WorldData.a(this.a).name(), Integer.valueOf(WorldData.a(this.a).getVersion()), Boolean.valueOf(WorldData.b(this.a))});
    }

    public Object call() {
        return this.a();
    }
}

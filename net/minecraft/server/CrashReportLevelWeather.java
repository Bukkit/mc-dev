package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportLevelWeather implements Callable {

    final WorldData a;

    CrashReportLevelWeather(WorldData worlddata) {
        this.a = worlddata;
    }

    public String a() {
        return String.format("Rain time: %d (now: %b), thunder time: %d (now: %b)", new Object[] { Integer.valueOf(WorldData.k(this.a)), Boolean.valueOf(WorldData.l(this.a)), Integer.valueOf(WorldData.m(this.a)), Boolean.valueOf(WorldData.n(this.a))});
    }

    public Object call() {
        return this.a();
    }
}

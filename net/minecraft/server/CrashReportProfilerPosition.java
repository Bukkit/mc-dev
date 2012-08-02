package net.minecraft.server;

import java.util.concurrent.Callable;

public class CrashReportProfilerPosition implements Callable {

    final MinecraftServer a;

    public CrashReportProfilerPosition(MinecraftServer minecraftserver) {
        this.a = minecraftserver;
    }

    public String a() {
        return this.a.methodProfiler.a ? this.a.methodProfiler.c() : "N/A (disabled)";
    }

    public Object call() {
        return this.a();
    }
}

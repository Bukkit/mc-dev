package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportMemory implements Callable {

    final CrashReport a;

    CrashReportMemory(CrashReport crashreport) {
        this.a = crashreport;
    }

    public String a() {
        Runtime runtime = Runtime.getRuntime();
        long i = runtime.maxMemory();
        long j = runtime.totalMemory();
        long k = runtime.freeMemory();
        long l = i / 1024L / 1024L;
        long i1 = j / 1024L / 1024L;
        long j1 = k / 1024L / 1024L;

        return k + " bytes (" + j1 + " MB) / " + j + " bytes (" + i1 + " MB) up to " + i + " bytes (" + l + " MB)";
    }

    public Object call() {
        return this.a();
    }
}

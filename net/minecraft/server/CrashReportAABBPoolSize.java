package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportAABBPoolSize implements Callable {

    final CrashReport a;

    CrashReportAABBPoolSize(CrashReport crashreport) {
        this.a = crashreport;
    }

    public String a() {
        int i = AxisAlignedBB.a().c();
        int j = 56 * i;
        int k = j / 1024 / 1024;
        int l = AxisAlignedBB.a().d();
        int i1 = 56 * l;
        int j1 = i1 / 1024 / 1024;

        return i + " (" + j + " bytes; " + k + " MB) allocated, " + l + " (" + i1 + " bytes; " + j1 + " MB) used";
    }

    public Object call() {
        return this.a();
    }
}

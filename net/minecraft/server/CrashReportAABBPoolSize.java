package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportAABBPoolSize implements Callable {

    final CrashReport a;

    CrashReportAABBPoolSize(CrashReport crashreport) {
        this.a = crashreport;
    }

    public String a() {
        byte b0 = 0;
        int i = 56 * b0;
        int j = i / 1024 / 1024;
        byte b1 = 0;
        int k = 56 * b1;
        int l = k / 1024 / 1024;

        return b0 + " (" + i + " bytes; " + j + " MB) allocated, " + b1 + " (" + k + " bytes; " + l + " MB) used";
    }

    public Object call() {
        return this.a();
    }
}

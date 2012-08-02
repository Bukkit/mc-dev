package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportJavaVersion implements Callable {

    final CrashReport a;

    CrashReportJavaVersion(CrashReport crashreport) {
        this.a = crashreport;
    }

    public String a() {
        return System.getProperty("java.version") + ", " + System.getProperty("java.vendor");
    }

    public Object call() {
        return this.a();
    }
}

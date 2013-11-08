package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportVersion implements Callable {

    final CrashReport a;

    CrashReportVersion(CrashReport crashreport) {
        this.a = crashreport;
    }

    public String a() {
        return "1.7.2";
    }

    public Object call() {
        return this.a();
    }
}

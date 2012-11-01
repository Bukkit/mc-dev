package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportIntCacheSize implements Callable {

    final CrashReport a;

    CrashReportIntCacheSize(CrashReport crashreport) {
        this.a = crashreport;
    }

    public String a() {
        return IntCache.b();
    }

    public Object call() {
        return this.a();
    }
}

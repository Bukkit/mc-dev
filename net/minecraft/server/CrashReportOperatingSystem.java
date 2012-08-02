package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportOperatingSystem implements Callable {

    final CrashReport a;

    CrashReportOperatingSystem(CrashReport crashreport) {
        this.a = crashreport;
    }

    public String a() {
        return System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version");
    }

    public Object call() {
        return this.a();
    }
}

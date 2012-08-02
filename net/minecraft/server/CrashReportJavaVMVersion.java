package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportJavaVMVersion implements Callable {

    final CrashReport a;

    CrashReportJavaVMVersion(CrashReport crashreport) {
        this.a = crashreport;
    }

    public String a() {
        return System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor");
    }

    public Object call() {
        return this.a();
    }
}

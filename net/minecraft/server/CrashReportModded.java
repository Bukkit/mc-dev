package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportModded implements Callable {

    final DedicatedServer a;

    CrashReportModded(DedicatedServer dedicatedserver) {
        this.a = dedicatedserver;
    }

    public String a() {
        String s = this.a.getServerModName();

        return !s.equals("vanilla") ? "Definitely; Server brand changed to \'" + s + "\'" : "Unknown (can\'t tell)";
    }

    public Object call() {
        return this.a();
    }
}

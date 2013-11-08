package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportEntityTrackerUpdateInterval implements Callable {

    final int a;
    final EntityTracker b;

    CrashReportEntityTrackerUpdateInterval(EntityTracker entitytracker, int i) {
        this.b = entitytracker;
        this.a = i;
    }

    public String a() {
        String s = "Once per " + this.a + " ticks";

        if (this.a == Integer.MAX_VALUE) {
            s = "Maximum (" + s + ")";
        }

        return s;
    }

    public Object call() {
        return this.a();
    }
}

package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportWorldLocation implements Callable {

    final int a;
    final int b;
    final World c;

    CrashReportWorldLocation(World world, int i, int j) {
        this.c = world;
        this.a = i;
        this.b = j;
    }

    public String a() {
        return CrashReportSystemDetails.a(this.a, 0, this.b);
    }

    public Object call() {
        return this.a();
    }
}

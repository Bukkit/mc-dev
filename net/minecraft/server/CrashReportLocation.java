package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportLocation implements Callable {

    final int a;
    final int b;
    final int c;
    final Chunk d;

    CrashReportLocation(Chunk chunk, int i, int j, int k) {
        this.d = chunk;
        this.a = i;
        this.b = j;
        this.c = k;
    }

    public String a() {
        return CrashReportSystemDetails.a(this.a, this.b, this.c);
    }

    public Object call() {
        return this.a();
    }
}

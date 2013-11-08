package net.minecraft.server;

import java.util.concurrent.Callable;

final class CrashReportGenLayer2 implements Callable {

    final int a;

    CrashReportGenLayer2(int i) {
        this.a = i;
    }

    public String a() {
        return String.valueOf(BiomeBase.getBiome(this.a));
    }

    public Object call() {
        return this.a();
    }
}

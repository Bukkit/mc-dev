package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportIsFeatureChunk implements Callable {

    final int a;
    final int b;
    final StructureGenerator c;

    CrashReportIsFeatureChunk(StructureGenerator structuregenerator, int i, int j) {
        this.c = structuregenerator;
        this.a = i;
        this.b = j;
    }

    public String a() {
        return this.c.a(this.a, this.b) ? "True" : "False";
    }

    public Object call() {
        return this.a();
    }
}

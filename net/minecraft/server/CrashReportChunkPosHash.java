package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportChunkPosHash implements Callable {

    final int a;
    final int b;
    final StructureGenerator c;

    CrashReportChunkPosHash(StructureGenerator structuregenerator, int i, int j) {
        this.c = structuregenerator;
        this.a = i;
        this.b = j;
    }

    public String a() {
        return String.valueOf(ChunkCoordIntPair.a(this.a, this.b));
    }

    public Object call() {
        return this.a();
    }
}

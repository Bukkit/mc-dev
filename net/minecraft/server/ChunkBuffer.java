package net.minecraft.server;

import java.io.ByteArrayOutputStream;

class ChunkBuffer extends ByteArrayOutputStream {

    private int b;
    private int c;

    final RegionFile a;

    public ChunkBuffer(RegionFile regionfile, int i, int j) {
        super(8096);
        this.a = regionfile;
        this.b = i;
        this.c = j;
    }

    public void close() {
        this.a.a(this.b, this.c, this.buf, this.count);
    }
}

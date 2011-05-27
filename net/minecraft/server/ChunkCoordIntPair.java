package net.minecraft.server;

public class ChunkCoordIntPair {

    public final int a;
    public final int b;

    public ChunkCoordIntPair(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public static int a(int i, int j) {
        return (i < 0 ? Integer.MIN_VALUE : 0) | (i & 32767) << 16 | (j < 0 ? '\u8000' : 0) | j & 32767;
    }

    public int hashCode() {
        return a(this.a, this.b);
    }

    public boolean equals(Object object) {
        ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) object;

        return chunkcoordintpair.a == this.a && chunkcoordintpair.b == this.b;
    }
}

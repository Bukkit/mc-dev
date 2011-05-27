package net.minecraft.server;

public class ChunkCoordIntPair {

    public final int x;
    public final int z;

    public ChunkCoordIntPair(int i, int j) {
        this.x = i;
        this.z = j;
    }

    public static int a(int i, int j) {
        return (i < 0 ? Integer.MIN_VALUE : 0) | (i & 32767) << 16 | (j < 0 ? '\u8000' : 0) | j & 32767;
    }

    public int hashCode() {
        return a(this.x, this.z);
    }

    public boolean equals(Object object) {
        ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) object;

        return chunkcoordintpair.x == this.x && chunkcoordintpair.z == this.z;
    }
}

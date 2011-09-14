package net.minecraft.server;

public class ChunkCoordIntPair {

    public final int x;
    public final int z;

    public ChunkCoordIntPair(int i, int j) {
        this.x = i;
        this.z = j;
    }

    public static long a(int i, int j) {
        long k = (long) i;
        long l = (long) j;

        return k & 4294967295L | (l & 4294967295L) << 32;
    }

    public int hashCode() {
        long i = a(this.x, this.z);
        int j = (int) i;
        int k = (int) (i >> 32);

        return j ^ k;
    }

    public boolean equals(Object object) {
        ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) object;

        return chunkcoordintpair.x == this.x && chunkcoordintpair.z == this.z;
    }
}

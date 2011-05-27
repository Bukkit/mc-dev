package net.minecraft.server;

public class ChunkCoordIntPair {

    public int a;
    public int b;

    public ChunkCoordIntPair(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public int hashCode() {
        return this.a << 8 | this.b;
    }

    public boolean equals(Object object) {
        ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) object;

        return chunkcoordintpair.a == this.a && chunkcoordintpair.b == this.b;
    }

    public double a(Entity entity) {
        double d0 = (double) (this.a * 16 + 8);
        double d1 = (double) (this.b * 16 + 8);
        double d2 = d0 - entity.l;
        double d3 = d1 - entity.n;

        return d2 * d2 + d3 * d3;
    }
}

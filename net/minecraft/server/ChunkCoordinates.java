package net.minecraft.server;

public class ChunkCoordinates implements Comparable {

    public int a;
    public int b;
    public int c;

    public ChunkCoordinates() {}

    public ChunkCoordinates(int i, int j, int k) {
        this.a = i;
        this.b = j;
        this.c = k;
    }

    public boolean equals(Object object) {
        if (!(object instanceof ChunkCoordinates)) {
            return false;
        } else {
            ChunkCoordinates chunkcoordinates = (ChunkCoordinates) object;

            return this.a == chunkcoordinates.a && this.b == chunkcoordinates.b && this.c == chunkcoordinates.c;
        }
    }

    public int hashCode() {
        return this.a + this.c << 8 + this.b << 16;
    }

    public int compareTo(ChunkCoordinates chunkcoordinates) {
        return this.b == chunkcoordinates.b ? (this.c == chunkcoordinates.c ? this.a - chunkcoordinates.a : this.c - chunkcoordinates.c) : this.b - chunkcoordinates.b;
    }
}

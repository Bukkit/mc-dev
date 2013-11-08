package net.minecraft.server;

public class ChunkCoordinates implements Comparable {

    public int x;
    public int y;
    public int z;

    public ChunkCoordinates() {}

    public ChunkCoordinates(int i, int j, int k) {
        this.x = i;
        this.y = j;
        this.z = k;
    }

    public ChunkCoordinates(ChunkCoordinates chunkcoordinates) {
        this.x = chunkcoordinates.x;
        this.y = chunkcoordinates.y;
        this.z = chunkcoordinates.z;
    }

    public boolean equals(Object object) {
        if (!(object instanceof ChunkCoordinates)) {
            return false;
        } else {
            ChunkCoordinates chunkcoordinates = (ChunkCoordinates) object;

            return this.x == chunkcoordinates.x && this.y == chunkcoordinates.y && this.z == chunkcoordinates.z;
        }
    }

    public int hashCode() {
        return this.x + this.z << 8 + this.y << 16;
    }

    public int compareTo(ChunkCoordinates chunkcoordinates) {
        return this.y == chunkcoordinates.y ? (this.z == chunkcoordinates.z ? this.x - chunkcoordinates.x : this.z - chunkcoordinates.z) : this.y - chunkcoordinates.y;
    }

    public void b(int i, int j, int k) {
        this.x = i;
        this.y = j;
        this.z = k;
    }

    public float e(int i, int j, int k) {
        float f = (float) (this.x - i);
        float f1 = (float) (this.y - j);
        float f2 = (float) (this.z - k);

        return f * f + f1 * f1 + f2 * f2;
    }

    public float e(ChunkCoordinates chunkcoordinates) {
        return this.e(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z);
    }

    public String toString() {
        return "Pos{x=" + this.x + ", y=" + this.y + ", z=" + this.z + '}';
    }

    public int compareTo(Object object) {
        return this.compareTo((ChunkCoordinates) object);
    }
}

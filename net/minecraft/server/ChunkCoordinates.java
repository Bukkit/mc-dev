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
        int l = this.x - i;
        int i1 = this.y - j;
        int j1 = this.z - k;

        return (float) (l * l + i1 * i1 + j1 * j1);
    }

    public float e(ChunkCoordinates chunkcoordinates) {
        return this.e(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z);
    }

    public int compareTo(Object object) {
        return this.compareTo((ChunkCoordinates) object);
    }
}

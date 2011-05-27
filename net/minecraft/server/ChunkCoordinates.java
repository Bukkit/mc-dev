package net.minecraft.server;

final class ChunkCoordinates {

    public final int a;
    public final int b;

    public ChunkCoordinates(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public boolean equals(Object object) {
        if (!(object instanceof ChunkCoordinates)) {
            return false;
        } else {
            ChunkCoordinates chunkcoordinates = (ChunkCoordinates) object;

            return this.a == chunkcoordinates.a && this.b == chunkcoordinates.b;
        }
    }

    public int hashCode() {
        return this.a << 16 ^ this.b;
    }
}

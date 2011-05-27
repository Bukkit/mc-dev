package net.minecraft.server;

public class ChunkPosition {

    public final int a;
    public final int b;
    public final int c;

    public ChunkPosition(int i, int j, int k) {
        this.a = i;
        this.b = j;
        this.c = k;
    }

    public boolean equals(Object object) {
        if (!(object instanceof ChunkPosition)) {
            return false;
        } else {
            ChunkPosition chunkposition = (ChunkPosition) object;

            return chunkposition.a == this.a && chunkposition.b == this.b && chunkposition.c == this.c;
        }
    }

    public int hashCode() {
        return this.a * 8976890 + this.b * 981131 + this.c;
    }
}

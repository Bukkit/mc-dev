package net.minecraft.server;

public class ChunkPosition {

    public final int x;
    public final int y;
    public final int z;

    public ChunkPosition(int i, int j, int k) {
        this.x = i;
        this.y = j;
        this.z = k;
    }

    public ChunkPosition(Vec3D vec3d) {
        this(MathHelper.floor(vec3d.a), MathHelper.floor(vec3d.b), MathHelper.floor(vec3d.c));
    }

    public boolean equals(Object object) {
        if (!(object instanceof ChunkPosition)) {
            return false;
        } else {
            ChunkPosition chunkposition = (ChunkPosition) object;

            return chunkposition.x == this.x && chunkposition.y == this.y && chunkposition.z == this.z;
        }
    }

    public int hashCode() {
        return this.x * 8976890 + this.y * 981131 + this.z;
    }
}

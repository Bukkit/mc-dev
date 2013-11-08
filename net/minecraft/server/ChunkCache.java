package net.minecraft.server;

public class ChunkCache implements IBlockAccess {

    private int a;
    private int b;
    private Chunk[][] c;
    private boolean d;
    private World e;

    public ChunkCache(World world, int i, int j, int k, int l, int i1, int j1, int k1) {
        this.e = world;
        this.a = i - k1 >> 4;
        this.b = k - k1 >> 4;
        int l1 = l + k1 >> 4;
        int i2 = j1 + k1 >> 4;

        this.c = new Chunk[l1 - this.a + 1][i2 - this.b + 1];
        this.d = true;

        int j2;
        int k2;
        Chunk chunk;

        for (j2 = this.a; j2 <= l1; ++j2) {
            for (k2 = this.b; k2 <= i2; ++k2) {
                chunk = world.getChunkAt(j2, k2);
                if (chunk != null) {
                    this.c[j2 - this.a][k2 - this.b] = chunk;
                }
            }
        }

        for (j2 = i >> 4; j2 <= l >> 4; ++j2) {
            for (k2 = k >> 4; k2 <= j1 >> 4; ++k2) {
                chunk = this.c[j2 - this.a][k2 - this.b];
                if (chunk != null && !chunk.c(j, i1)) {
                    this.d = false;
                }
            }
        }
    }

    public Block getType(int i, int j, int k) {
        Block block = Blocks.AIR;

        if (j >= 0 && j < 256) {
            int l = (i >> 4) - this.a;
            int i1 = (k >> 4) - this.b;

            if (l >= 0 && l < this.c.length && i1 >= 0 && i1 < this.c[l].length) {
                Chunk chunk = this.c[l][i1];

                if (chunk != null) {
                    block = chunk.getType(i & 15, j, k & 15);
                }
            }
        }

        return block;
    }

    public TileEntity getTileEntity(int i, int j, int k) {
        int l = (i >> 4) - this.a;
        int i1 = (k >> 4) - this.b;

        return this.c[l][i1].e(i & 15, j, k & 15);
    }

    public int getData(int i, int j, int k) {
        if (j < 0) {
            return 0;
        } else if (j >= 256) {
            return 0;
        } else {
            int l = (i >> 4) - this.a;
            int i1 = (k >> 4) - this.b;

            return this.c[l][i1].getData(i & 15, j, k & 15);
        }
    }

    public Vec3DPool getVec3DPool() {
        return this.e.getVec3DPool();
    }

    public int getBlockPower(int i, int j, int k, int l) {
        return this.getType(i, j, k).c(this, i, j, k, l);
    }
}

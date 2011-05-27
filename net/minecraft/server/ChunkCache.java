package net.minecraft.server;

public class ChunkCache implements IBlockAccess {

    private int a;
    private int b;
    private Chunk[][] c;
    private World d;

    public ChunkCache(World world, int i, int j, int k, int l, int i1, int j1) {
        this.d = world;
        this.a = i >> 4;
        this.b = k >> 4;
        int k1 = l >> 4;
        int l1 = j1 >> 4;

        this.c = new Chunk[k1 - this.a + 1][l1 - this.b + 1];

        for (int i2 = this.a; i2 <= k1; ++i2) {
            for (int j2 = this.b; j2 <= l1; ++j2) {
                this.c[i2 - this.a][j2 - this.b] = world.c(i2, j2);
            }
        }
    }

    public int a(int i, int j, int k) {
        if (j < 0) {
            return 0;
        } else if (j >= 128) {
            return 0;
        } else {
            int l = (i >> 4) - this.a;
            int i1 = (k >> 4) - this.b;

            return this.c[l][i1].a(i & 15, j, k & 15);
        }
    }

    public int b(int i, int j, int k) {
        if (j < 0) {
            return 0;
        } else if (j >= 128) {
            return 0;
        } else {
            int l = (i >> 4) - this.a;
            int i1 = (k >> 4) - this.b;

            return this.c[l][i1].b(i & 15, j, k & 15);
        }
    }

    public Material c(int i, int j, int k) {
        int l = this.a(i, j, k);

        return l == 0 ? Material.a : Block.n[l].bt;
    }

    public boolean d(int i, int j, int k) {
        Block block = Block.n[this.a(i, j, k)];

        return block == null ? false : block.a();
    }
}

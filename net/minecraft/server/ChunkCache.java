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
                this.c[i2 - this.a][j2 - this.b] = world.getChunkAt(i2, j2);
            }
        }
    }

    public int getTypeId(int i, int j, int k) {
        if (j < 0) {
            return 0;
        } else if (j >= 128) {
            return 0;
        } else {
            int l = (i >> 4) - this.a;
            int i1 = (k >> 4) - this.b;

            if (l >= 0 && l < this.c.length && i1 >= 0 && i1 < this.c[l].length) {
                Chunk chunk = this.c[l][i1];

                return chunk == null ? 0 : chunk.getTypeId(i & 15, j, k & 15);
            } else {
                return 0;
            }
        }
    }

    public TileEntity getTileEntity(int i, int j, int k) {
        int l = (i >> 4) - this.a;
        int i1 = (k >> 4) - this.b;

        return this.c[l][i1].d(i & 15, j, k & 15);
    }

    public int getData(int i, int j, int k) {
        if (j < 0) {
            return 0;
        } else if (j >= 128) {
            return 0;
        } else {
            int l = (i >> 4) - this.a;
            int i1 = (k >> 4) - this.b;

            return this.c[l][i1].getData(i & 15, j, k & 15);
        }
    }

    public Material getMaterial(int i, int j, int k) {
        int l = this.getTypeId(i, j, k);

        return l == 0 ? Material.AIR : Block.byId[l].material;
    }

    public boolean e(int i, int j, int k) {
        Block block = Block.byId[this.getTypeId(i, j, k)];

        return block == null ? false : block.material.isSolid() && block.b();
    }
}

package net.minecraft.server;

public class GenLayerMushroomShore extends GenLayer {

    public GenLayerMushroomShore(long i, GenLayer genlayer) {
        super(i);
        this.a = genlayer;
    }

    public int[] a(int i, int j, int k, int l) {
        int[] aint = this.a.a(i - 1, j - 1, k + 2, l + 2);
        int[] aint1 = IntCache.a(k * l);

        for (int i1 = 0; i1 < l; ++i1) {
            for (int j1 = 0; j1 < k; ++j1) {
                this.a((long) (j1 + i), (long) (i1 + j));
                int k1 = aint[j1 + 1 + (i1 + 1) * (k + 2)];

                if (k1 == BiomeBase.MUSHROOM_ISLAND.F) {
                    int l1 = aint[j1 + 1 + (i1 + 1 - 1) * (k + 2)];
                    int i2 = aint[j1 + 1 + 1 + (i1 + 1) * (k + 2)];
                    int j2 = aint[j1 + 1 - 1 + (i1 + 1) * (k + 2)];
                    int k2 = aint[j1 + 1 + (i1 + 1 + 1) * (k + 2)];

                    if (l1 != BiomeBase.OCEAN.F && i2 != BiomeBase.OCEAN.F && j2 != BiomeBase.OCEAN.F && k2 != BiomeBase.OCEAN.F) {
                        aint1[j1 + i1 * k] = k1;
                    } else {
                        aint1[j1 + i1 * k] = BiomeBase.MUSHROOM_SHORE.F;
                    }
                } else {
                    aint1[j1 + i1 * k] = k1;
                }
            }
        }

        return aint1;
    }
}

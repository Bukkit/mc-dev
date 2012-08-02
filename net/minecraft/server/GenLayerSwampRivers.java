package net.minecraft.server;

public class GenLayerSwampRivers extends GenLayer {

    public GenLayerSwampRivers(long i, GenLayer genlayer) {
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

                if ((k1 != BiomeBase.SWAMPLAND.id || this.a(6) != 0) && (k1 != BiomeBase.JUNGLE.id && k1 != BiomeBase.JUNGLE_HILLS.id || this.a(8) != 0)) {
                    aint1[j1 + i1 * k] = k1;
                } else {
                    aint1[j1 + i1 * k] = BiomeBase.RIVER.id;
                }
            }
        }

        return aint1;
    }
}

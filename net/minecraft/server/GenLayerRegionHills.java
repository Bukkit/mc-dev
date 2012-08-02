package net.minecraft.server;

public class GenLayerRegionHills extends GenLayer {

    public GenLayerRegionHills(long i, GenLayer genlayer) {
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

                if (this.a(3) == 0) {
                    int l1 = k1;

                    if (k1 == BiomeBase.DESERT.id) {
                        l1 = BiomeBase.DESERT_HILLS.id;
                    } else if (k1 == BiomeBase.FOREST.id) {
                        l1 = BiomeBase.FOREST_HILLS.id;
                    } else if (k1 == BiomeBase.TAIGA.id) {
                        l1 = BiomeBase.TAIGA_HILLS.id;
                    } else if (k1 == BiomeBase.PLAINS.id) {
                        l1 = BiomeBase.FOREST.id;
                    } else if (k1 == BiomeBase.ICE_PLAINS.id) {
                        l1 = BiomeBase.ICE_MOUNTAINS.id;
                    } else if (k1 == BiomeBase.JUNGLE.id) {
                        l1 = BiomeBase.JUNGLE_HILLS.id;
                    }

                    if (l1 == k1) {
                        aint1[j1 + i1 * k] = k1;
                    } else {
                        int i2 = aint[j1 + 1 + (i1 + 1 - 1) * (k + 2)];
                        int j2 = aint[j1 + 1 + 1 + (i1 + 1) * (k + 2)];
                        int k2 = aint[j1 + 1 - 1 + (i1 + 1) * (k + 2)];
                        int l2 = aint[j1 + 1 + (i1 + 1 + 1) * (k + 2)];

                        if (i2 == k1 && j2 == k1 && k2 == k1 && l2 == k1) {
                            aint1[j1 + i1 * k] = l1;
                        } else {
                            aint1[j1 + i1 * k] = k1;
                        }
                    }
                } else {
                    aint1[j1 + i1 * k] = k1;
                }
            }
        }

        return aint1;
    }
}

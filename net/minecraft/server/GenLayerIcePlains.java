package net.minecraft.server;

public class GenLayerIcePlains extends GenLayer {

    public GenLayerIcePlains(long i, GenLayer genlayer) {
        super(i);
        this.a = genlayer;
    }

    public int[] a(int i, int j, int k, int l) {
        int i1 = i - 1;
        int j1 = j - 1;
        int k1 = k + 2;
        int l1 = l + 2;
        int[] aint = this.a.a(i1, j1, k1, l1);
        int[] aint1 = IntCache.a(k * l);

        for (int i2 = 0; i2 < l; ++i2) {
            for (int j2 = 0; j2 < k; ++j2) {
                int k2 = aint[j2 + 1 + (i2 + 1) * k1];

                this.a((long) (j2 + i), (long) (i2 + j));
                if (k2 == 0) {
                    aint1[j2 + i2 * k] = 0;
                } else {
                    int l2 = this.a(5);

                    if (l2 == 0) {
                        l2 = BiomeBase.ICE_PLAINS.F;
                    } else {
                        l2 = 1;
                    }

                    aint1[j2 + i2 * k] = l2;
                }
            }
        }

        return aint1;
    }
}

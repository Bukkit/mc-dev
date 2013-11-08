package net.minecraft.server;

public class GenLayerDeepOcean extends GenLayer {

    public GenLayerDeepOcean(long i, GenLayer genlayer) {
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
                int k2 = aint[j2 + 1 + (i2 + 1 - 1) * (k + 2)];
                int l2 = aint[j2 + 1 + 1 + (i2 + 1) * (k + 2)];
                int i3 = aint[j2 + 1 - 1 + (i2 + 1) * (k + 2)];
                int j3 = aint[j2 + 1 + (i2 + 1 + 1) * (k + 2)];
                int k3 = aint[j2 + 1 + (i2 + 1) * k1];
                int l3 = 0;

                if (k2 == 0) {
                    ++l3;
                }

                if (l2 == 0) {
                    ++l3;
                }

                if (i3 == 0) {
                    ++l3;
                }

                if (j3 == 0) {
                    ++l3;
                }

                if (k3 == 0 && l3 > 3) {
                    aint1[j2 + i2 * k] = BiomeBase.DEEP_OCEAN.id;
                } else {
                    aint1[j2 + i2 * k] = k3;
                }
            }
        }

        return aint1;
    }
}

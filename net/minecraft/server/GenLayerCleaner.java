package net.minecraft.server;

public class GenLayerCleaner extends GenLayer {

    public GenLayerCleaner(long i, GenLayer genlayer) {
        super(i);
        this.a = genlayer;
    }

    public int[] a(int i, int j, int k, int l) {
        int[] aint = this.a.a(i, j, k, l);
        int[] aint1 = IntCache.a(k * l);

        for (int i1 = 0; i1 < l; ++i1) {
            for (int j1 = 0; j1 < k; ++j1) {
                this.a((long) (j1 + i), (long) (i1 + j));
                aint1[j1 + i1 * k] = aint[j1 + i1 * k] > 0 ? this.a(299999) + 2 : 0;
            }
        }

        return aint1;
    }
}

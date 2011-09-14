package net.minecraft.server;

public class GenLayerTemperature extends GenLayer {

    public GenLayerTemperature(GenLayer genlayer) {
        super(0L);
        this.a = genlayer;
    }

    public int[] a(int i, int j, int k, int l) {
        int[] aint = this.a.a(i, j, k, l);
        int[] aint1 = IntCache.a(k * l);

        for (int i1 = 0; i1 < k * l; ++i1) {
            aint1[i1] = BiomeBase.a[aint[i1]].f();
        }

        return aint1;
    }
}

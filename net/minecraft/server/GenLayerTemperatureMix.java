package net.minecraft.server;

public class GenLayerTemperatureMix extends GenLayer {

    private GenLayer b;
    private int c;

    public GenLayerTemperatureMix(GenLayer genlayer, GenLayer genlayer1, int i) {
        super(0L);
        this.a = genlayer1;
        this.b = genlayer;
        this.c = i;
    }

    public int[] a(int i, int j, int k, int l) {
        int[] aint = this.a.a(i, j, k, l);
        int[] aint1 = this.b.a(i, j, k, l);
        int[] aint2 = IntCache.a(k * l);

        for (int i1 = 0; i1 < k * l; ++i1) {
            aint2[i1] = aint1[i1] + (BiomeBase.a[aint[i1]].f() - aint1[i1]) / (this.c * 2 + 1);
        }

        return aint2;
    }
}

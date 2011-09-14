package net.minecraft.server;

public class GenLayerRiverMix extends GenLayer {

    private GenLayer b;
    private GenLayer c;

    public GenLayerRiverMix(long i, GenLayer genlayer, GenLayer genlayer1) {
        super(i);
        this.b = genlayer;
        this.c = genlayer1;
    }

    public void b(long i) {
        this.b.b(i);
        this.c.b(i);
        super.b(i);
    }

    public int[] a(int i, int j, int k, int l) {
        int[] aint = this.b.a(i, j, k, l);
        int[] aint1 = this.c.a(i, j, k, l);
        int[] aint2 = IntCache.a(k * l);

        for (int i1 = 0; i1 < k * l; ++i1) {
            if (aint[i1] == BiomeBase.OCEAN.y) {
                aint2[i1] = aint[i1];
            } else {
                aint2[i1] = aint1[i1] >= 0 ? aint1[i1] : aint[i1];
            }
        }

        return aint2;
    }
}

package net.minecraft.server;

public class GenLayerRiverMix extends GenLayer {

    private GenLayer c;
    private GenLayer d;

    public GenLayerRiverMix(long i, GenLayer genlayer, GenLayer genlayer1) {
        super(i);
        this.c = genlayer;
        this.d = genlayer1;
    }

    public void a(long i) {
        this.c.a(i);
        this.d.a(i);
        super.a(i);
    }

    public int[] a(int i, int j, int k, int l) {
        int[] aint = this.c.a(i, j, k, l);
        int[] aint1 = this.d.a(i, j, k, l);
        int[] aint2 = IntCache.a(k * l);

        for (int i1 = 0; i1 < k * l; ++i1) {
            if (aint[i1] != BiomeBase.OCEAN.id && aint[i1] != BiomeBase.DEEP_OCEAN.id) {
                if (aint1[i1] == BiomeBase.RIVER.id) {
                    if (aint[i1] == BiomeBase.ICE_PLAINS.id) {
                        aint2[i1] = BiomeBase.FROZEN_RIVER.id;
                    } else if (aint[i1] != BiomeBase.MUSHROOM_ISLAND.id && aint[i1] != BiomeBase.MUSHROOM_SHORE.id) {
                        aint2[i1] = aint1[i1] & 255;
                    } else {
                        aint2[i1] = BiomeBase.MUSHROOM_SHORE.id;
                    }
                } else {
                    aint2[i1] = aint[i1];
                }
            } else {
                aint2[i1] = aint[i1];
            }
        }

        return aint2;
    }
}

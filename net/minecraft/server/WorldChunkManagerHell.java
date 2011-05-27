package net.minecraft.server;

import java.util.Arrays;

public class WorldChunkManagerHell extends WorldChunkManager {

    private BiomeBase e;
    private double f;
    private double g;

    public WorldChunkManagerHell(BiomeBase biomebase, double d0, double d1) {
        this.e = biomebase;
        this.f = d0;
        this.g = d1;
    }

    public BiomeBase a(ChunkCoordIntPair chunkcoordintpair) {
        return this.e;
    }

    public BiomeBase a(int i, int j) {
        return this.e;
    }

    public BiomeBase[] a(int i, int j, int k, int l) {
        this.d = this.a(this.d, i, j, k, l);
        return this.d;
    }

    public double[] a(double[] adouble, int i, int j, int k, int l) {
        if (adouble == null || adouble.length < k * l) {
            adouble = new double[k * l];
        }

        Arrays.fill(adouble, 0, k * l, this.f);
        return adouble;
    }

    public BiomeBase[] a(BiomeBase[] abiomebase, int i, int j, int k, int l) {
        if (abiomebase == null || abiomebase.length < k * l) {
            abiomebase = new BiomeBase[k * l];
            this.a = new double[k * l];
            this.b = new double[k * l];
        }

        Arrays.fill(abiomebase, 0, k * l, this.e);
        Arrays.fill(this.b, 0, k * l, this.g);
        Arrays.fill(this.a, 0, k * l, this.f);
        return abiomebase;
    }
}

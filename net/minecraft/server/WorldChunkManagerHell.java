package net.minecraft.server;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldChunkManagerHell extends WorldChunkManager {

    private BiomeBase b;
    private float c;
    private float d;

    public WorldChunkManagerHell(BiomeBase biomebase, float f, float f1) {
        this.b = biomebase;
        this.c = f;
        this.d = f1;
    }

    public BiomeBase a(ChunkCoordIntPair chunkcoordintpair) {
        return this.b;
    }

    public BiomeBase getBiome(int i, int j) {
        return this.b;
    }

    public float[] getTemperatures(float[] afloat, int i, int j, int k, int l) {
        if (afloat == null || afloat.length < k * l) {
            afloat = new float[k * l];
        }

        Arrays.fill(afloat, 0, k * l, this.c);
        return afloat;
    }

    public float[] a(int i, int j, int k, int l) {
        return this.getTemperatures(new float[k * l], i, j, k, l);
    }

    public float[] getWetness(float[] afloat, int i, int j, int k, int l) {
        if (afloat == null || afloat.length < k * l) {
            afloat = new float[k * l];
        }

        Arrays.fill(afloat, 0, k * l, this.d);
        return afloat;
    }

    public BiomeBase[] a(BiomeBase[] abiomebase, int i, int j, int k, int l) {
        if (abiomebase == null || abiomebase.length < k * l) {
            abiomebase = new BiomeBase[k * l];
        }

        Arrays.fill(abiomebase, 0, k * l, this.b);
        return abiomebase;
    }

    public BiomeBase[] a(BiomeBase[] abiomebase, int i, int j, int k, int l, boolean flag) {
        return this.a(abiomebase, i, j, k, l);
    }

    public ChunkPosition a(int i, int j, int k, List list, Random random) {
        return list.contains(this.b) ? new ChunkPosition(i - k + random.nextInt(k * 2 + 1), 0, j - k + random.nextInt(k * 2 + 1)) : null;
    }

    public boolean a(int i, int j, int k, List list) {
        return list.contains(this.b);
    }
}

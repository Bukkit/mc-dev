package net.minecraft.server;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldChunkManagerHell extends WorldChunkManager {

    private BiomeBase a;
    private float b;
    private float c;

    public WorldChunkManagerHell(BiomeBase biomebase, float f, float f1) {
        this.a = biomebase;
        this.b = f;
        this.c = f1;
    }

    public BiomeBase a(ChunkCoordIntPair chunkcoordintpair) {
        return this.a;
    }

    public BiomeBase getBiome(int i, int j) {
        return this.a;
    }

    public float[] a(float[] afloat, int i, int j, int k, int l) {
        if (afloat == null || afloat.length < k * l) {
            afloat = new float[k * l];
        }

        Arrays.fill(afloat, 0, k * l, this.b);
        return afloat;
    }

    public float[] b(float[] afloat, int i, int j, int k, int l) {
        if (afloat == null || afloat.length < k * l) {
            afloat = new float[k * l];
        }

        Arrays.fill(afloat, 0, k * l, this.c);
        return afloat;
    }

    public BiomeBase[] a(BiomeBase[] abiomebase, int i, int j, int k, int l) {
        if (abiomebase == null || abiomebase.length < k * l) {
            abiomebase = new BiomeBase[k * l];
        }

        Arrays.fill(abiomebase, 0, k * l, this.a);
        return abiomebase;
    }

    public ChunkPosition a(int i, int j, int k, List list, Random random) {
        return list.contains(this.a) ? new ChunkPosition(i - k + random.nextInt(k * 2 + 1), 0, j - k + random.nextInt(k * 2 + 1)) : null;
    }

    public boolean a(int i, int j, int k, List list) {
        return list.contains(this.a);
    }
}

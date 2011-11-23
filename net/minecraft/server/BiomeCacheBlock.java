package net.minecraft.server;

public class BiomeCacheBlock {

    public float[] a;
    public float[] b;
    public BiomeBase[] c;
    public int d;
    public int e;
    public long f;

    final BiomeCache g;

    public BiomeCacheBlock(BiomeCache biomecache, int i, int j) {
        this.g = biomecache;
        this.a = new float[256];
        this.b = new float[256];
        this.c = new BiomeBase[256];
        this.d = i;
        this.e = j;
        BiomeCache.a(biomecache).getTemperatures(this.a, i << 4, j << 4, 16, 16);
        BiomeCache.a(biomecache).getWetness(this.b, i << 4, j << 4, 16, 16);
        BiomeCache.a(biomecache).a(this.c, i << 4, j << 4, 16, 16, false);
    }

    public BiomeBase a(int i, int j) {
        return this.c[i & 15 | (j & 15) << 4];
    }

    public float b(int i, int j) {
        return this.a[i & 15 | (j & 15) << 4];
    }
}

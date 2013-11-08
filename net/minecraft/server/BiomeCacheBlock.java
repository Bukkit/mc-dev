package net.minecraft.server;

public class BiomeCacheBlock {

    public float[] a;
    public BiomeBase[] b;
    public int c;
    public int d;
    public long e;
    final BiomeCache f;

    public BiomeCacheBlock(BiomeCache biomecache, int i, int j) {
        this.f = biomecache;
        this.a = new float[256];
        this.b = new BiomeBase[256];
        this.c = i;
        this.d = j;
        BiomeCache.a(biomecache).getWetness(this.a, i << 4, j << 4, 16, 16);
        BiomeCache.a(biomecache).a(this.b, i << 4, j << 4, 16, 16, false);
    }

    public BiomeBase a(int i, int j) {
        return this.b[i & 15 | (j & 15) << 4];
    }
}

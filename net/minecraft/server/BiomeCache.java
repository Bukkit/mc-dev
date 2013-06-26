package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class BiomeCache {

    private final WorldChunkManager a;
    private long b;
    private LongHashMap c = new LongHashMap();
    private List d = new ArrayList();

    public BiomeCache(WorldChunkManager worldchunkmanager) {
        this.a = worldchunkmanager;
    }

    public BiomeCacheBlock a(int i, int j) {
        i >>= 4;
        j >>= 4;
        long k = (long) i & 4294967295L | ((long) j & 4294967295L) << 32;
        BiomeCacheBlock biomecacheblock = (BiomeCacheBlock) this.c.getEntry(k);

        if (biomecacheblock == null) {
            biomecacheblock = new BiomeCacheBlock(this, i, j);
            this.c.put(k, biomecacheblock);
            this.d.add(biomecacheblock);
        }

        biomecacheblock.f = MinecraftServer.aq();
        return biomecacheblock;
    }

    public BiomeBase b(int i, int j) {
        return this.a(i, j).a(i, j);
    }

    public void a() {
        long i = MinecraftServer.aq();
        long j = i - this.b;

        if (j > 7500L || j < 0L) {
            this.b = i;

            for (int k = 0; k < this.d.size(); ++k) {
                BiomeCacheBlock biomecacheblock = (BiomeCacheBlock) this.d.get(k);
                long l = i - biomecacheblock.f;

                if (l > 30000L || l < 0L) {
                    this.d.remove(k--);
                    long i1 = (long) biomecacheblock.d & 4294967295L | ((long) biomecacheblock.e & 4294967295L) << 32;

                    this.c.remove(i1);
                }
            }
        }
    }

    public BiomeBase[] e(int i, int j) {
        return this.a(i, j).c;
    }

    static WorldChunkManager a(BiomeCache biomecache) {
        return biomecache.a;
    }
}

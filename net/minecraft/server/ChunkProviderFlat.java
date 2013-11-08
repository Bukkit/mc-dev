package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ChunkProviderFlat implements IChunkProvider {

    private World a;
    private Random b;
    private final Block[] c = new Block[256];
    private final byte[] d = new byte[256];
    private final WorldGenFlatInfo e;
    private final List f = new ArrayList();
    private final boolean g;
    private final boolean h;
    private WorldGenLakes i;
    private WorldGenLakes j;

    public ChunkProviderFlat(World world, long i, boolean flag, String s) {
        this.a = world;
        this.b = new Random(i);
        this.e = WorldGenFlatInfo.a(s);
        if (flag) {
            Map map = this.e.b();

            if (map.containsKey("village")) {
                Map map1 = (Map) map.get("village");

                if (!map1.containsKey("size")) {
                    map1.put("size", "1");
                }

                this.f.add(new WorldGenVillage(map1));
            }

            if (map.containsKey("biome_1")) {
                this.f.add(new WorldGenLargeFeature((Map) map.get("biome_1")));
            }

            if (map.containsKey("mineshaft")) {
                this.f.add(new WorldGenMineshaft((Map) map.get("mineshaft")));
            }

            if (map.containsKey("stronghold")) {
                this.f.add(new WorldGenStronghold((Map) map.get("stronghold")));
            }
        }

        this.g = this.e.b().containsKey("decoration");
        if (this.e.b().containsKey("lake")) {
            this.i = new WorldGenLakes(Blocks.STATIONARY_WATER);
        }

        if (this.e.b().containsKey("lava_lake")) {
            this.j = new WorldGenLakes(Blocks.STATIONARY_LAVA);
        }

        this.h = this.e.b().containsKey("dungeon");
        Iterator iterator = this.e.c().iterator();

        while (iterator.hasNext()) {
            WorldGenFlatLayerInfo worldgenflatlayerinfo = (WorldGenFlatLayerInfo) iterator.next();

            for (int j = worldgenflatlayerinfo.d(); j < worldgenflatlayerinfo.d() + worldgenflatlayerinfo.a(); ++j) {
                this.c[j] = worldgenflatlayerinfo.b();
                this.d[j] = (byte) worldgenflatlayerinfo.c();
            }
        }
    }

    public Chunk getChunkAt(int i, int j) {
        return this.getOrCreateChunk(i, j);
    }

    public Chunk getOrCreateChunk(int i, int j) {
        Chunk chunk = new Chunk(this.a, i, j);

        int k;

        for (int l = 0; l < this.c.length; ++l) {
            Block block = this.c[l];

            if (block != null) {
                k = l >> 4;
                ChunkSection chunksection = chunk.i()[k];

                if (chunksection == null) {
                    chunksection = new ChunkSection(l, !this.a.worldProvider.g);
                    chunk.i()[k] = chunksection;
                }

                for (int i1 = 0; i1 < 16; ++i1) {
                    for (int j1 = 0; j1 < 16; ++j1) {
                        chunksection.setTypeId(i1, l & 15, j1, block);
                        chunksection.setData(i1, l & 15, j1, this.d[l]);
                    }
                }
            }
        }

        chunk.initLighting();
        BiomeBase[] abiomebase = this.a.getWorldChunkManager().getBiomeBlock((BiomeBase[]) null, i * 16, j * 16, 16, 16);
        byte[] abyte = chunk.m();

        for (k = 0; k < abyte.length; ++k) {
            abyte[k] = (byte) abiomebase[k].id;
        }

        Iterator iterator = this.f.iterator();

        while (iterator.hasNext()) {
            StructureGenerator structuregenerator = (StructureGenerator) iterator.next();

            structuregenerator.a(this, this.a, i, j, (Block[]) null);
        }

        chunk.initLighting();
        return chunk;
    }

    public boolean isChunkLoaded(int i, int j) {
        return true;
    }

    public void getChunkAt(IChunkProvider ichunkprovider, int i, int j) {
        int k = i * 16;
        int l = j * 16;
        BiomeBase biomebase = this.a.getBiome(k + 16, l + 16);
        boolean flag = false;

        this.b.setSeed(this.a.getSeed());
        long i1 = this.b.nextLong() / 2L * 2L + 1L;
        long j1 = this.b.nextLong() / 2L * 2L + 1L;

        this.b.setSeed((long) i * i1 + (long) j * j1 ^ this.a.getSeed());
        Iterator iterator = this.f.iterator();

        while (iterator.hasNext()) {
            StructureGenerator structuregenerator = (StructureGenerator) iterator.next();
            boolean flag1 = structuregenerator.a(this.a, this.b, i, j);

            if (structuregenerator instanceof WorldGenVillage) {
                flag |= flag1;
            }
        }

        int k1;
        int l1;
        int i2;

        if (this.i != null && !flag && this.b.nextInt(4) == 0) {
            l1 = k + this.b.nextInt(16) + 8;
            k1 = this.b.nextInt(256);
            i2 = l + this.b.nextInt(16) + 8;
            this.i.a(this.a, this.b, l1, k1, i2);
        }

        if (this.j != null && !flag && this.b.nextInt(8) == 0) {
            l1 = k + this.b.nextInt(16) + 8;
            k1 = this.b.nextInt(this.b.nextInt(248) + 8);
            i2 = l + this.b.nextInt(16) + 8;
            if (k1 < 63 || this.b.nextInt(10) == 0) {
                this.j.a(this.a, this.b, l1, k1, i2);
            }
        }

        if (this.h) {
            for (l1 = 0; l1 < 8; ++l1) {
                k1 = k + this.b.nextInt(16) + 8;
                i2 = this.b.nextInt(256);
                int j2 = l + this.b.nextInt(16) + 8;

                (new WorldGenDungeons()).a(this.a, this.b, k1, i2, j2);
            }
        }

        if (this.g) {
            biomebase.a(this.a, this.b, k, l);
        }
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
        return true;
    }

    public void b() {}

    public boolean unloadChunks() {
        return false;
    }

    public boolean canSave() {
        return true;
    }

    public String getName() {
        return "FlatLevelSource";
    }

    public List getMobsFor(EnumCreatureType enumcreaturetype, int i, int j, int k) {
        BiomeBase biomebase = this.a.getBiome(i, k);

        return biomebase.getMobs(enumcreaturetype);
    }

    public ChunkPosition findNearestMapFeature(World world, String s, int i, int j, int k) {
        if ("Stronghold".equals(s)) {
            Iterator iterator = this.f.iterator();

            while (iterator.hasNext()) {
                StructureGenerator structuregenerator = (StructureGenerator) iterator.next();

                if (structuregenerator instanceof WorldGenStronghold) {
                    return structuregenerator.getNearestGeneratedFeature(world, i, j, k);
                }
            }
        }

        return null;
    }

    public int getLoadedChunks() {
        return 0;
    }

    public void recreateStructures(int i, int j) {
        Iterator iterator = this.f.iterator();

        while (iterator.hasNext()) {
            StructureGenerator structuregenerator = (StructureGenerator) iterator.next();

            structuregenerator.a(this, this.a, i, j, (Block[]) null);
        }
    }
}

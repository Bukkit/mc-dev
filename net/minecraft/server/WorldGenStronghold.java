package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGenStronghold extends StructureGenerator {

    private BiomeBase[] a;
    private boolean f;
    private ChunkCoordIntPair[] g;

    public WorldGenStronghold() {
        this.a = new BiomeBase[] { BiomeBase.DESERT, BiomeBase.FOREST, BiomeBase.EXTREME_HILLS, BiomeBase.SWAMPLAND, BiomeBase.TAIGA, BiomeBase.ICE_PLAINS, BiomeBase.ICE_MOUNTAINS, BiomeBase.DESERT_HILLS, BiomeBase.FOREST_HILLS, BiomeBase.SMALL_MOUNTAINS};
        this.g = new ChunkCoordIntPair[3];
    }

    protected boolean a(int i, int j) {
        if (!this.f) {
            Random random = new Random();

            random.setSeed(this.d.getSeed());
            double d0 = random.nextDouble() * 3.141592653589793D * 2.0D;

            for (int k = 0; k < this.g.length; ++k) {
                double d1 = (1.25D + random.nextDouble()) * 32.0D;
                int l = (int) Math.round(Math.cos(d0) * d1);
                int i1 = (int) Math.round(Math.sin(d0) * d1);
                ArrayList arraylist = new ArrayList();
                BiomeBase[] abiomebase = this.a;
                int j1 = abiomebase.length;

                for (int k1 = 0; k1 < j1; ++k1) {
                    BiomeBase biomebase = abiomebase[k1];

                    arraylist.add(biomebase);
                }

                ChunkPosition chunkposition = this.d.getWorldChunkManager().a((l << 4) + 8, (i1 << 4) + 8, 112, arraylist, random);

                if (chunkposition != null) {
                    l = chunkposition.x >> 4;
                    i1 = chunkposition.z >> 4;
                } else {
                    System.out.println("Placed stronghold in INVALID biome at (" + l + ", " + i1 + ")");
                }

                this.g[k] = new ChunkCoordIntPair(l, i1);
                d0 += 6.283185307179586D / (double) this.g.length;
            }

            this.f = true;
        }

        ChunkCoordIntPair[] achunkcoordintpair = this.g;
        int l1 = achunkcoordintpair.length;

        for (int i2 = 0; i2 < l1; ++i2) {
            ChunkCoordIntPair chunkcoordintpair = achunkcoordintpair[i2];

            if (i == chunkcoordintpair.x && j == chunkcoordintpair.z) {
                System.out.println(i + ", " + j);
                return true;
            }
        }

        return false;
    }

    protected List a() {
        ArrayList arraylist = new ArrayList();
        ChunkCoordIntPair[] achunkcoordintpair = this.g;
        int i = achunkcoordintpair.length;

        for (int j = 0; j < i; ++j) {
            ChunkCoordIntPair chunkcoordintpair = achunkcoordintpair[j];

            if (chunkcoordintpair != null) {
                arraylist.add(chunkcoordintpair.a(64));
            }
        }

        return arraylist;
    }

    protected StructureStart b(int i, int j) {
        WorldGenStrongholdStart worldgenstrongholdstart;

        for (worldgenstrongholdstart = new WorldGenStrongholdStart(this.d, this.c, i, j); worldgenstrongholdstart.c().isEmpty() || ((WorldGenStrongholdStairs2) worldgenstrongholdstart.c().get(0)).b == null; worldgenstrongholdstart = new WorldGenStrongholdStart(this.d, this.c, i, j)) {
            ;
        }

        return worldgenstrongholdstart;
    }
}

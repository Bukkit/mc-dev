package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class WorldGenStronghold extends StructureGenerator {

    private BiomeBase[] a;
    private boolean f;
    private ChunkCoordIntPair[] g;

    public WorldGenStronghold() {
        this.a = new BiomeBase[] { BiomeBase.DESERT, BiomeBase.FOREST, BiomeBase.EXTREME_HILLS, BiomeBase.SWAMPLAND, BiomeBase.TAIGA, BiomeBase.ICE_PLAINS, BiomeBase.ICE_MOUNTAINS};
        this.g = new ChunkCoordIntPair[3];
    }

    protected boolean a(int i, int j) {
        int k;

        if (!this.f) {
            this.c.setSeed(this.d.getSeed());
            double d0 = this.c.nextDouble() * 3.141592653589793D * 2.0D;

            for (k = 0; k < this.g.length; ++k) {
                double d1 = (1.25D + this.c.nextDouble()) * 32.0D;
                int l = (int) Math.round(Math.cos(d0) * d1);
                int i1 = (int) Math.round(Math.sin(d0) * d1);
                ArrayList arraylist = new ArrayList();
                BiomeBase[] abiomebase = this.a;
                int j1 = abiomebase.length;

                for (int k1 = 0; k1 < j1; ++k1) {
                    BiomeBase biomebase = abiomebase[k1];

                    arraylist.add(biomebase);
                }

                ChunkPosition chunkposition = this.d.getWorldChunkManager().a((l << 4) + 8, (i1 << 4) + 8, 112, arraylist, this.c);

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

        for (k = 0; k < l1; ++k) {
            ChunkCoordIntPair chunkcoordintpair = achunkcoordintpair[k];

            if (i == chunkcoordintpair.x && j == chunkcoordintpair.z) {
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

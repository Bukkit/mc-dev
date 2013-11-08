package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldChunkManager {

    private GenLayer c;
    private GenLayer d;
    private BiomeCache e;
    private List f;

    protected WorldChunkManager() {
        this.e = new BiomeCache(this);
        this.f = new ArrayList();
        this.f.add(BiomeBase.FOREST);
        this.f.add(BiomeBase.PLAINS);
        this.f.add(BiomeBase.TAIGA);
        this.f.add(BiomeBase.TAIGA_HILLS);
        this.f.add(BiomeBase.FOREST_HILLS);
        this.f.add(BiomeBase.JUNGLE);
        this.f.add(BiomeBase.JUNGLE_HILLS);
    }

    public WorldChunkManager(long i, WorldType worldtype) {
        this();
        GenLayer[] agenlayer = GenLayer.a(i, worldtype);

        this.c = agenlayer[0];
        this.d = agenlayer[1];
    }

    public WorldChunkManager(World world) {
        this(world.getSeed(), world.getWorldData().getType());
    }

    public List a() {
        return this.f;
    }

    public BiomeBase getBiome(int i, int j) {
        return this.e.b(i, j);
    }

    public float[] getWetness(float[] afloat, int i, int j, int k, int l) {
        IntCache.a();
        if (afloat == null || afloat.length < k * l) {
            afloat = new float[k * l];
        }

        int[] aint = this.d.a(i, j, k, l);

        for (int i1 = 0; i1 < k * l; ++i1) {
            try {
                float f = (float) BiomeBase.getBiome(aint[i1]).h() / 65536.0F;

                if (f > 1.0F) {
                    f = 1.0F;
                }

                afloat[i1] = f;
            } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.a(throwable, "Invalid Biome id");
                CrashReportSystemDetails crashreportsystemdetails = crashreport.a("DownfallBlock");

                crashreportsystemdetails.a("biome id", Integer.valueOf(i1));
                crashreportsystemdetails.a("downfalls[] size", Integer.valueOf(afloat.length));
                crashreportsystemdetails.a("x", Integer.valueOf(i));
                crashreportsystemdetails.a("z", Integer.valueOf(j));
                crashreportsystemdetails.a("w", Integer.valueOf(k));
                crashreportsystemdetails.a("h", Integer.valueOf(l));
                throw new ReportedException(crashreport);
            }
        }

        return afloat;
    }

    public BiomeBase[] getBiomes(BiomeBase[] abiomebase, int i, int j, int k, int l) {
        IntCache.a();
        if (abiomebase == null || abiomebase.length < k * l) {
            abiomebase = new BiomeBase[k * l];
        }

        int[] aint = this.c.a(i, j, k, l);

        try {
            for (int i1 = 0; i1 < k * l; ++i1) {
                abiomebase[i1] = BiomeBase.getBiome(aint[i1]);
            }

            return abiomebase;
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Invalid Biome id");
            CrashReportSystemDetails crashreportsystemdetails = crashreport.a("RawBiomeBlock");

            crashreportsystemdetails.a("biomes[] size", Integer.valueOf(abiomebase.length));
            crashreportsystemdetails.a("x", Integer.valueOf(i));
            crashreportsystemdetails.a("z", Integer.valueOf(j));
            crashreportsystemdetails.a("w", Integer.valueOf(k));
            crashreportsystemdetails.a("h", Integer.valueOf(l));
            throw new ReportedException(crashreport);
        }
    }

    public BiomeBase[] getBiomeBlock(BiomeBase[] abiomebase, int i, int j, int k, int l) {
        return this.a(abiomebase, i, j, k, l, true);
    }

    public BiomeBase[] a(BiomeBase[] abiomebase, int i, int j, int k, int l, boolean flag) {
        IntCache.a();
        if (abiomebase == null || abiomebase.length < k * l) {
            abiomebase = new BiomeBase[k * l];
        }

        if (flag && k == 16 && l == 16 && (i & 15) == 0 && (j & 15) == 0) {
            BiomeBase[] abiomebase1 = this.e.d(i, j);

            System.arraycopy(abiomebase1, 0, abiomebase, 0, k * l);
            return abiomebase;
        } else {
            int[] aint = this.d.a(i, j, k, l);

            for (int i1 = 0; i1 < k * l; ++i1) {
                abiomebase[i1] = BiomeBase.getBiome(aint[i1]);
            }

            return abiomebase;
        }
    }

    public boolean a(int i, int j, int k, List list) {
        IntCache.a();
        int l = i - k >> 2;
        int i1 = j - k >> 2;
        int j1 = i + k >> 2;
        int k1 = j + k >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.c.a(l, i1, l1, i2);

        try {
            for (int j2 = 0; j2 < l1 * i2; ++j2) {
                BiomeBase biomebase = BiomeBase.getBiome(aint[j2]);

                if (!list.contains(biomebase)) {
                    return false;
                }
            }

            return true;
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Invalid Biome id");
            CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Layer");

            crashreportsystemdetails.a("Layer", this.c.toString());
            crashreportsystemdetails.a("x", Integer.valueOf(i));
            crashreportsystemdetails.a("z", Integer.valueOf(j));
            crashreportsystemdetails.a("radius", Integer.valueOf(k));
            crashreportsystemdetails.a("allowed", list);
            throw new ReportedException(crashreport);
        }
    }

    public ChunkPosition a(int i, int j, int k, List list, Random random) {
        IntCache.a();
        int l = i - k >> 2;
        int i1 = j - k >> 2;
        int j1 = i + k >> 2;
        int k1 = j + k >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.c.a(l, i1, l1, i2);
        ChunkPosition chunkposition = null;
        int j2 = 0;

        for (int k2 = 0; k2 < l1 * i2; ++k2) {
            int l2 = l + k2 % l1 << 2;
            int i3 = i1 + k2 / l1 << 2;
            BiomeBase biomebase = BiomeBase.getBiome(aint[k2]);

            if (list.contains(biomebase) && (chunkposition == null || random.nextInt(j2 + 1) == 0)) {
                chunkposition = new ChunkPosition(l2, 0, i3);
                ++j2;
            }
        }

        return chunkposition;
    }

    public void b() {
        this.e.a();
    }
}

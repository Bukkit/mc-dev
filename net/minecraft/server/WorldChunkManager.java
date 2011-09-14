package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldChunkManager {

    private GenLayer temperature;
    private GenLayer rain;
    private GenLayer c;
    private GenLayer d;
    private BiomeCache e;
    private List f;

    protected WorldChunkManager() {
        this.e = new BiomeCache(this);
        this.f = new ArrayList();
        this.f.add(BiomeBase.FOREST);
        this.f.add(BiomeBase.SWAMPLAND);
        this.f.add(BiomeBase.TAIGA);
    }

    public WorldChunkManager(World world) {
        this();
        GenLayer[] agenlayer = GenLayer.a(world.getSeed());

        this.temperature = agenlayer[0];
        this.rain = agenlayer[1];
        this.c = agenlayer[2];
        this.d = agenlayer[3];
    }

    public List a() {
        return this.f;
    }

    public BiomeBase a(ChunkCoordIntPair chunkcoordintpair) {
        return this.getBiome(chunkcoordintpair.x << 4, chunkcoordintpair.z << 4);
    }

    public BiomeBase getBiome(int i, int j) {
        return this.e.a(i, j);
    }

    public float[] b(float[] afloat, int i, int j, int k, int l) {
        IntCache.a();
        if (afloat == null || afloat.length < k * l) {
            afloat = new float[k * l];
        }

        int[] aint = this.d.a(i, j, k, l);

        for (int i1 = 0; i1 < k * l; ++i1) {
            float f = (float) aint[i1] / 65536.0F;

            if (f > 1.0F) {
                f = 1.0F;
            }

            afloat[i1] = f;
        }

        return afloat;
    }

    public float[] a(float[] afloat, int i, int j, int k, int l) {
        IntCache.a();
        if (afloat == null || afloat.length < k * l) {
            afloat = new float[k * l];
        }

        int[] aint = this.c.a(i, j, k, l);

        for (int i1 = 0; i1 < k * l; ++i1) {
            float f = (float) aint[i1] / 65536.0F;

            if (f > 1.0F) {
                f = 1.0F;
            }

            afloat[i1] = f;
        }

        return afloat;
    }

    public BiomeBase[] b(BiomeBase[] abiomebase, int i, int j, int k, int l) {
        IntCache.a();
        if (abiomebase == null || abiomebase.length < k * l) {
            abiomebase = new BiomeBase[k * l];
        }

        int[] aint = this.temperature.a(i, j, k, l);

        for (int i1 = 0; i1 < k * l; ++i1) {
            abiomebase[i1] = BiomeBase.a[aint[i1]];
        }

        return abiomebase;
    }

    public BiomeBase[] a(BiomeBase[] abiomebase, int i, int j, int k, int l) {
        return this.a(abiomebase, i, j, k, l, true);
    }

    public BiomeBase[] a(BiomeBase[] abiomebase, int i, int j, int k, int l, boolean flag) {
        IntCache.a();
        if (abiomebase == null || abiomebase.length < k * l) {
            abiomebase = new BiomeBase[k * l];
        }

        if (flag && k == 16 && l == 16 && (i & 15) == 0 && (j & 15) == 0) {
            BiomeBase[] abiomebase1 = this.e.b(i, j);

            System.arraycopy(abiomebase1, 0, abiomebase, 0, k * l);
            return abiomebase;
        } else {
            int[] aint = this.rain.a(i, j, k, l);

            for (int i1 = 0; i1 < k * l; ++i1) {
                abiomebase[i1] = BiomeBase.a[aint[i1]];
            }

            return abiomebase;
        }
    }

    public boolean a(int i, int j, int k, List list) {
        int l = i - k >> 2;
        int i1 = j - k >> 2;
        int j1 = i + k >> 2;
        int k1 = j + k >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.temperature.a(l, i1, l1, i2);

        for (int j2 = 0; j2 < l1 * i2; ++j2) {
            BiomeBase biomebase = BiomeBase.a[aint[j2]];

            if (!list.contains(biomebase)) {
                return false;
            }
        }

        return true;
    }

    public ChunkPosition a(int i, int j, int k, List list, Random random) {
        int l = i - k >> 2;
        int i1 = j - k >> 2;
        int j1 = i + k >> 2;
        int k1 = j + k >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.temperature.a(l, i1, l1, i2);
        ChunkPosition chunkposition = null;
        int j2 = 0;

        for (int k2 = 0; k2 < aint.length; ++k2) {
            int l2 = l + k2 % l1 << 2;
            int i3 = i1 + k2 / l1 << 2;
            BiomeBase biomebase = BiomeBase.a[aint[k2]];

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

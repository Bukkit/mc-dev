package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class ChunkProviderGenerate implements IChunkProvider {

    private Random i;
    private NoiseGeneratorOctaves j;
    private NoiseGeneratorOctaves k;
    private NoiseGeneratorOctaves l;
    private NoiseGenerator3 m;
    public NoiseGeneratorOctaves a;
    public NoiseGeneratorOctaves b;
    public NoiseGeneratorOctaves c;
    private World n;
    private final boolean o;
    private WorldType p;
    private final double[] q;
    private final float[] r;
    private double[] s = new double[256];
    private WorldGenBase t = new WorldGenCaves();
    private WorldGenStronghold u = new WorldGenStronghold();
    private WorldGenVillage v = new WorldGenVillage();
    private WorldGenMineshaft w = new WorldGenMineshaft();
    private WorldGenLargeFeature x = new WorldGenLargeFeature();
    private WorldGenBase y = new WorldGenCanyon();
    private BiomeBase[] z;
    double[] d;
    double[] e;
    double[] f;
    double[] g;
    int[][] h = new int[32][32];

    public ChunkProviderGenerate(World world, long i, boolean flag) {
        this.n = world;
        this.o = flag;
        this.p = world.getWorldData().getType();
        this.i = new Random(i);
        this.j = new NoiseGeneratorOctaves(this.i, 16);
        this.k = new NoiseGeneratorOctaves(this.i, 16);
        this.l = new NoiseGeneratorOctaves(this.i, 8);
        this.m = new NoiseGenerator3(this.i, 4);
        this.a = new NoiseGeneratorOctaves(this.i, 10);
        this.b = new NoiseGeneratorOctaves(this.i, 16);
        this.c = new NoiseGeneratorOctaves(this.i, 8);
        this.q = new double[825];
        this.r = new float[25];

        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                float f = 10.0F / MathHelper.c((float) (j * j + k * k) + 0.2F);

                this.r[j + 2 + (k + 2) * 5] = f;
            }
        }
    }

    public void a(int i, int j, Block[] ablock) {
        byte b0 = 63;

        this.z = this.n.getWorldChunkManager().getBiomes(this.z, i * 4 - 2, j * 4 - 2, 10, 10);
        this.a(i * 4, 0, j * 4);

        for (int k = 0; k < 4; ++k) {
            int l = k * 5;
            int i1 = (k + 1) * 5;

            for (int j1 = 0; j1 < 4; ++j1) {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;

                for (int k2 = 0; k2 < 32; ++k2) {
                    double d0 = 0.125D;
                    double d1 = this.q[k1 + k2];
                    double d2 = this.q[l1 + k2];
                    double d3 = this.q[i2 + k2];
                    double d4 = this.q[j2 + k2];
                    double d5 = (this.q[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.q[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.q[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.q[j2 + k2 + 1] - d4) * d0;

                    for (int l2 = 0; l2 < 8; ++l2) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i3 = 0; i3 < 4; ++i3) {
                            int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
                            short short1 = 256;

                            j3 -= short1;
                            double d14 = 0.25D;
                            double d15 = (d11 - d10) * d14;
                            double d16 = d10 - d15;

                            for (int k3 = 0; k3 < 4; ++k3) {
                                if ((d16 += d15) > 0.0D) {
                                    ablock[j3 += short1] = Blocks.STONE;
                                } else if (k2 * 8 + l2 < b0) {
                                    ablock[j3 += short1] = Blocks.STATIONARY_WATER;
                                } else {
                                    ablock[j3 += short1] = null;
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void a(int i, int j, Block[] ablock, byte[] abyte, BiomeBase[] abiomebase) {
        double d0 = 0.03125D;

        this.s = this.m.a(this.s, (double) (i * 16), (double) (j * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                BiomeBase biomebase = abiomebase[l + k * 16];

                biomebase.a(this.n, this.i, ablock, abyte, i * 16 + k, j * 16 + l, this.s[l + k * 16]);
            }
        }
    }

    public Chunk getChunkAt(int i, int j) {
        return this.getOrCreateChunk(i, j);
    }

    public Chunk getOrCreateChunk(int i, int j) {
        this.i.setSeed((long) i * 341873128712L + (long) j * 132897987541L);
        Block[] ablock = new Block[65536];
        byte[] abyte = new byte[65536];

        this.a(i, j, ablock);
        this.z = this.n.getWorldChunkManager().getBiomeBlock(this.z, i * 16, j * 16, 16, 16);
        this.a(i, j, ablock, abyte, this.z);
        this.t.a(this, this.n, i, j, ablock);
        this.y.a(this, this.n, i, j, ablock);
        if (this.o) {
            this.w.a(this, this.n, i, j, ablock);
            this.v.a(this, this.n, i, j, ablock);
            this.u.a(this, this.n, i, j, ablock);
            this.x.a(this, this.n, i, j, ablock);
        }

        Chunk chunk = new Chunk(this.n, ablock, abyte, i, j);
        byte[] abyte1 = chunk.m();

        for (int k = 0; k < abyte1.length; ++k) {
            abyte1[k] = (byte) this.z[k].id;
        }

        chunk.initLighting();
        return chunk;
    }

    private void a(int i, int j, int k) {
        double d0 = 684.412D;
        double d1 = 684.412D;
        double d2 = 512.0D;
        double d3 = 512.0D;

        this.g = this.b.a(this.g, i, k, 5, 5, 200.0D, 200.0D, 0.5D);
        this.d = this.l.a(this.d, i, j, k, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
        this.e = this.j.a(this.e, i, j, k, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        this.f = this.k.a(this.f, i, j, k, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        boolean flag = false;
        boolean flag1 = false;
        int l = 0;
        int i1 = 0;
        double d4 = 8.5D;

        for (int j1 = 0; j1 < 5; ++j1) {
            for (int k1 = 0; k1 < 5; ++k1) {
                float f = 0.0F;
                float f1 = 0.0F;
                float f2 = 0.0F;
                byte b0 = 2;
                BiomeBase biomebase = this.z[j1 + 2 + (k1 + 2) * 10];

                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        BiomeBase biomebase1 = this.z[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                        float f3 = biomebase1.am;
                        float f4 = biomebase1.an;

                        if (this.p == WorldType.AMPLIFIED && f3 > 0.0F) {
                            f3 = 1.0F + f3 * 2.0F;
                            f4 = 1.0F + f4 * 4.0F;
                        }

                        float f5 = this.r[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0F);

                        if (biomebase1.am > biomebase.am) {
                            f5 /= 2.0F;
                        }

                        f += f4 * f5;
                        f1 += f3 * f5;
                        f2 += f5;
                    }
                }

                f /= f2;
                f1 /= f2;
                f = f * 0.9F + 0.1F;
                f1 = (f1 * 4.0F - 1.0F) / 8.0F;
                double d5 = this.g[i1] / 8000.0D;

                if (d5 < 0.0D) {
                    d5 = -d5 * 0.3D;
                }

                d5 = d5 * 3.0D - 2.0D;
                if (d5 < 0.0D) {
                    d5 /= 2.0D;
                    if (d5 < -1.0D) {
                        d5 = -1.0D;
                    }

                    d5 /= 1.4D;
                    d5 /= 2.0D;
                } else {
                    if (d5 > 1.0D) {
                        d5 = 1.0D;
                    }

                    d5 /= 8.0D;
                }

                ++i1;
                double d6 = (double) f1;
                double d7 = (double) f;

                d6 += d5 * 0.2D;
                d6 = d6 * 8.5D / 8.0D;
                double d8 = 8.5D + d6 * 4.0D;

                for (int j2 = 0; j2 < 33; ++j2) {
                    double d9 = ((double) j2 - d8) * 12.0D * 128.0D / 256.0D / d7;

                    if (d9 < 0.0D) {
                        d9 *= 4.0D;
                    }

                    double d10 = this.e[l] / 512.0D;
                    double d11 = this.f[l] / 512.0D;
                    double d12 = (this.d[l] / 10.0D + 1.0D) / 2.0D;
                    double d13 = MathHelper.b(d10, d11, d12) - d9;

                    if (j2 > 29) {
                        double d14 = (double) ((float) (j2 - 29) / 3.0F);

                        d13 = d13 * (1.0D - d14) + -10.0D * d14;
                    }

                    this.q[l] = d13;
                    ++l;
                }
            }
        }
    }

    public boolean isChunkLoaded(int i, int j) {
        return true;
    }

    public void getChunkAt(IChunkProvider ichunkprovider, int i, int j) {
        BlockFalling.instaFall = true;
        int k = i * 16;
        int l = j * 16;
        BiomeBase biomebase = this.n.getBiome(k + 16, l + 16);

        this.i.setSeed(this.n.getSeed());
        long i1 = this.i.nextLong() / 2L * 2L + 1L;
        long j1 = this.i.nextLong() / 2L * 2L + 1L;

        this.i.setSeed((long) i * i1 + (long) j * j1 ^ this.n.getSeed());
        boolean flag = false;

        if (this.o) {
            this.w.a(this.n, this.i, i, j);
            flag = this.v.a(this.n, this.i, i, j);
            this.u.a(this.n, this.i, i, j);
            this.x.a(this.n, this.i, i, j);
        }

        int k1;
        int l1;
        int i2;

        if (biomebase != BiomeBase.DESERT && biomebase != BiomeBase.DESERT_HILLS && !flag && this.i.nextInt(4) == 0) {
            k1 = k + this.i.nextInt(16) + 8;
            l1 = this.i.nextInt(256);
            i2 = l + this.i.nextInt(16) + 8;
            (new WorldGenLakes(Blocks.STATIONARY_WATER)).a(this.n, this.i, k1, l1, i2);
        }

        if (!flag && this.i.nextInt(8) == 0) {
            k1 = k + this.i.nextInt(16) + 8;
            l1 = this.i.nextInt(this.i.nextInt(248) + 8);
            i2 = l + this.i.nextInt(16) + 8;
            if (l1 < 63 || this.i.nextInt(10) == 0) {
                (new WorldGenLakes(Blocks.STATIONARY_LAVA)).a(this.n, this.i, k1, l1, i2);
            }
        }

        for (k1 = 0; k1 < 8; ++k1) {
            l1 = k + this.i.nextInt(16) + 8;
            i2 = this.i.nextInt(256);
            int j2 = l + this.i.nextInt(16) + 8;

            (new WorldGenDungeons()).a(this.n, this.i, l1, i2, j2);
        }

        biomebase.a(this.n, this.i, k, l);
        SpawnerCreature.a(this.n, biomebase, k + 8, l + 8, 16, 16, this.i);
        k += 8;
        l += 8;

        for (k1 = 0; k1 < 16; ++k1) {
            for (l1 = 0; l1 < 16; ++l1) {
                i2 = this.n.h(k + k1, l + l1);
                if (this.n.r(k1 + k, i2 - 1, l1 + l)) {
                    this.n.setTypeAndData(k1 + k, i2 - 1, l1 + l, Blocks.ICE, 0, 2);
                }

                if (this.n.e(k1 + k, i2, l1 + l, true)) {
                    this.n.setTypeAndData(k1 + k, i2, l1 + l, Blocks.SNOW, 0, 2);
                }
            }
        }

        BlockFalling.instaFall = false;
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
        return "RandomLevelSource";
    }

    public List getMobsFor(EnumCreatureType enumcreaturetype, int i, int j, int k) {
        BiomeBase biomebase = this.n.getBiome(i, k);

        return enumcreaturetype == EnumCreatureType.MONSTER && this.x.a(i, j, k) ? this.x.b() : biomebase.getMobs(enumcreaturetype);
    }

    public ChunkPosition findNearestMapFeature(World world, String s, int i, int j, int k) {
        return "Stronghold".equals(s) && this.u != null ? this.u.getNearestGeneratedFeature(world, i, j, k) : null;
    }

    public int getLoadedChunks() {
        return 0;
    }

    public void recreateStructures(int i, int j) {
        if (this.o) {
            this.w.a(this, this.n, i, j, (Block[]) null);
            this.v.a(this, this.n, i, j, (Block[]) null);
            this.u.a(this, this.n, i, j, (Block[]) null);
            this.x.a(this, this.n, i, j, (Block[]) null);
        }
    }
}

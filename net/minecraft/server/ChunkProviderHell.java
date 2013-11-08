package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class ChunkProviderHell implements IChunkProvider {

    private Random i;
    private NoiseGeneratorOctaves j;
    private NoiseGeneratorOctaves k;
    private NoiseGeneratorOctaves l;
    private NoiseGeneratorOctaves m;
    private NoiseGeneratorOctaves n;
    public NoiseGeneratorOctaves a;
    public NoiseGeneratorOctaves b;
    private World o;
    private double[] p;
    public WorldGenNether c = new WorldGenNether();
    private double[] q = new double[256];
    private double[] r = new double[256];
    private double[] s = new double[256];
    private WorldGenBase t = new WorldGenCavesHell();
    double[] d;
    double[] e;
    double[] f;
    double[] g;
    double[] h;

    public ChunkProviderHell(World world, long i) {
        this.o = world;
        this.i = new Random(i);
        this.j = new NoiseGeneratorOctaves(this.i, 16);
        this.k = new NoiseGeneratorOctaves(this.i, 16);
        this.l = new NoiseGeneratorOctaves(this.i, 8);
        this.m = new NoiseGeneratorOctaves(this.i, 4);
        this.n = new NoiseGeneratorOctaves(this.i, 4);
        this.a = new NoiseGeneratorOctaves(this.i, 10);
        this.b = new NoiseGeneratorOctaves(this.i, 16);
    }

    public void a(int i, int j, Block[] ablock) {
        byte b0 = 4;
        byte b1 = 32;
        int k = b0 + 1;
        byte b2 = 17;
        int l = b0 + 1;

        this.p = this.a(this.p, i * b0, 0, j * b0, k, b2, l);

        for (int i1 = 0; i1 < b0; ++i1) {
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k1 = 0; k1 < 16; ++k1) {
                    double d0 = 0.125D;
                    double d1 = this.p[((i1 + 0) * l + j1 + 0) * b2 + k1 + 0];
                    double d2 = this.p[((i1 + 0) * l + j1 + 1) * b2 + k1 + 0];
                    double d3 = this.p[((i1 + 1) * l + j1 + 0) * b2 + k1 + 0];
                    double d4 = this.p[((i1 + 1) * l + j1 + 1) * b2 + k1 + 0];
                    double d5 = (this.p[((i1 + 0) * l + j1 + 0) * b2 + k1 + 1] - d1) * d0;
                    double d6 = (this.p[((i1 + 0) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
                    double d7 = (this.p[((i1 + 1) * l + j1 + 0) * b2 + k1 + 1] - d3) * d0;
                    double d8 = (this.p[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4) * d0;

                    for (int l1 = 0; l1 < 8; ++l1) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i2 = 0; i2 < 4; ++i2) {
                            int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
                            short short1 = 128;
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int k2 = 0; k2 < 4; ++k2) {
                                Block block = null;

                                if (k1 * 8 + l1 < b1) {
                                    block = Blocks.STATIONARY_LAVA;
                                }

                                if (d15 > 0.0D) {
                                    block = Blocks.NETHERRACK;
                                }

                                ablock[j2] = block;
                                j2 += short1;
                                d15 += d16;
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

    public void b(int i, int j, Block[] ablock) {
        byte b0 = 64;
        double d0 = 0.03125D;

        this.q = this.m.a(this.q, i * 16, j * 16, 0, 16, 16, 1, d0, d0, 1.0D);
        this.r = this.m.a(this.r, i * 16, 109, j * 16, 16, 1, 16, d0, 1.0D, d0);
        this.s = this.n.a(this.s, i * 16, j * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                boolean flag = this.q[k + l * 16] + this.i.nextDouble() * 0.2D > 0.0D;
                boolean flag1 = this.r[k + l * 16] + this.i.nextDouble() * 0.2D > 0.0D;
                int i1 = (int) (this.s[k + l * 16] / 3.0D + 3.0D + this.i.nextDouble() * 0.25D);
                int j1 = -1;
                Block block = Blocks.NETHERRACK;
                Block block1 = Blocks.NETHERRACK;

                for (int k1 = 127; k1 >= 0; --k1) {
                    int l1 = (l * 16 + k) * 128 + k1;

                    if (k1 < 127 - this.i.nextInt(5) && k1 > 0 + this.i.nextInt(5)) {
                        Block block2 = ablock[l1];

                        if (block2 != null && block2.getMaterial() != Material.AIR) {
                            if (block2 == Blocks.NETHERRACK) {
                                if (j1 == -1) {
                                    if (i1 <= 0) {
                                        block = null;
                                        block1 = Blocks.NETHERRACK;
                                    } else if (k1 >= b0 - 4 && k1 <= b0 + 1) {
                                        block = Blocks.NETHERRACK;
                                        block1 = Blocks.NETHERRACK;
                                        if (flag1) {
                                            block = Blocks.GRAVEL;
                                            block1 = Blocks.NETHERRACK;
                                        }

                                        if (flag) {
                                            block = Blocks.SOUL_SAND;
                                            block1 = Blocks.SOUL_SAND;
                                        }
                                    }

                                    if (k1 < b0 && (block == null || block.getMaterial() == Material.AIR)) {
                                        block = Blocks.STATIONARY_LAVA;
                                    }

                                    j1 = i1;
                                    if (k1 >= b0 - 1) {
                                        ablock[l1] = block;
                                    } else {
                                        ablock[l1] = block1;
                                    }
                                } else if (j1 > 0) {
                                    --j1;
                                    ablock[l1] = block1;
                                }
                            }
                        } else {
                            j1 = -1;
                        }
                    } else {
                        ablock[l1] = Blocks.BEDROCK;
                    }
                }
            }
        }
    }

    public Chunk getChunkAt(int i, int j) {
        return this.getOrCreateChunk(i, j);
    }

    public Chunk getOrCreateChunk(int i, int j) {
        this.i.setSeed((long) i * 341873128712L + (long) j * 132897987541L);
        Block[] ablock = new Block['\u8000'];

        this.a(i, j, ablock);
        this.b(i, j, ablock);
        this.t.a(this, this.o, i, j, ablock);
        this.c.a(this, this.o, i, j, ablock);
        Chunk chunk = new Chunk(this.o, ablock, i, j);
        BiomeBase[] abiomebase = this.o.getWorldChunkManager().getBiomeBlock((BiomeBase[]) null, i * 16, j * 16, 16, 16);
        byte[] abyte = chunk.m();

        for (int k = 0; k < abyte.length; ++k) {
            abyte[k] = (byte) abiomebase[k].id;
        }

        chunk.n();
        return chunk;
    }

    private double[] a(double[] adouble, int i, int j, int k, int l, int i1, int j1) {
        if (adouble == null) {
            adouble = new double[l * i1 * j1];
        }

        double d0 = 684.412D;
        double d1 = 2053.236D;

        this.g = this.a.a(this.g, i, j, k, l, 1, j1, 1.0D, 0.0D, 1.0D);
        this.h = this.b.a(this.h, i, j, k, l, 1, j1, 100.0D, 0.0D, 100.0D);
        this.d = this.l.a(this.d, i, j, k, l, i1, j1, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
        this.e = this.j.a(this.e, i, j, k, l, i1, j1, d0, d1, d0);
        this.f = this.k.a(this.f, i, j, k, l, i1, j1, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;
        double[] adouble1 = new double[i1];

        int i2;

        for (i2 = 0; i2 < i1; ++i2) {
            adouble1[i2] = Math.cos((double) i2 * 3.141592653589793D * 6.0D / (double) i1) * 2.0D;
            double d2 = (double) i2;

            if (i2 > i1 / 2) {
                d2 = (double) (i1 - 1 - i2);
            }

            if (d2 < 4.0D) {
                d2 = 4.0D - d2;
                adouble1[i2] -= d2 * d2 * d2 * 10.0D;
            }
        }

        for (i2 = 0; i2 < l; ++i2) {
            for (int j2 = 0; j2 < j1; ++j2) {
                double d3 = (this.g[l1] + 256.0D) / 512.0D;

                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                double d4 = 0.0D;
                double d5 = this.h[l1] / 8000.0D;

                if (d5 < 0.0D) {
                    d5 = -d5;
                }

                d5 = d5 * 3.0D - 3.0D;
                if (d5 < 0.0D) {
                    d5 /= 2.0D;
                    if (d5 < -1.0D) {
                        d5 = -1.0D;
                    }

                    d5 /= 1.4D;
                    d5 /= 2.0D;
                    d3 = 0.0D;
                } else {
                    if (d5 > 1.0D) {
                        d5 = 1.0D;
                    }

                    d5 /= 6.0D;
                }

                d3 += 0.5D;
                d5 = d5 * (double) i1 / 16.0D;
                ++l1;

                for (int k2 = 0; k2 < i1; ++k2) {
                    double d6 = 0.0D;
                    double d7 = adouble1[k2];
                    double d8 = this.e[k1] / 512.0D;
                    double d9 = this.f[k1] / 512.0D;
                    double d10 = (this.d[k1] / 10.0D + 1.0D) / 2.0D;

                    if (d10 < 0.0D) {
                        d6 = d8;
                    } else if (d10 > 1.0D) {
                        d6 = d9;
                    } else {
                        d6 = d8 + (d9 - d8) * d10;
                    }

                    d6 -= d7;
                    double d11;

                    if (k2 > i1 - 4) {
                        d11 = (double) ((float) (k2 - (i1 - 4)) / 3.0F);
                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    if ((double) k2 < d4) {
                        d11 = (d4 - (double) k2) / 4.0D;
                        if (d11 < 0.0D) {
                            d11 = 0.0D;
                        }

                        if (d11 > 1.0D) {
                            d11 = 1.0D;
                        }

                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    adouble[k1] = d6;
                    ++k1;
                }
            }
        }

        return adouble;
    }

    public boolean isChunkLoaded(int i, int j) {
        return true;
    }

    public void getChunkAt(IChunkProvider ichunkprovider, int i, int j) {
        BlockFalling.instaFall = true;
        int k = i * 16;
        int l = j * 16;

        this.c.a(this.o, this.i, i, j);

        int i1;
        int j1;
        int k1;
        int l1;

        for (i1 = 0; i1 < 8; ++i1) {
            j1 = k + this.i.nextInt(16) + 8;
            k1 = this.i.nextInt(120) + 4;
            l1 = l + this.i.nextInt(16) + 8;
            (new WorldGenHellLava(Blocks.LAVA, false)).a(this.o, this.i, j1, k1, l1);
        }

        i1 = this.i.nextInt(this.i.nextInt(10) + 1) + 1;

        int i2;

        for (j1 = 0; j1 < i1; ++j1) {
            k1 = k + this.i.nextInt(16) + 8;
            l1 = this.i.nextInt(120) + 4;
            i2 = l + this.i.nextInt(16) + 8;
            (new WorldGenFire()).a(this.o, this.i, k1, l1, i2);
        }

        i1 = this.i.nextInt(this.i.nextInt(10) + 1);

        for (j1 = 0; j1 < i1; ++j1) {
            k1 = k + this.i.nextInt(16) + 8;
            l1 = this.i.nextInt(120) + 4;
            i2 = l + this.i.nextInt(16) + 8;
            (new WorldGenLightStone1()).a(this.o, this.i, k1, l1, i2);
        }

        for (j1 = 0; j1 < 10; ++j1) {
            k1 = k + this.i.nextInt(16) + 8;
            l1 = this.i.nextInt(128);
            i2 = l + this.i.nextInt(16) + 8;
            (new WorldGenLightStone2()).a(this.o, this.i, k1, l1, i2);
        }

        if (this.i.nextInt(1) == 0) {
            j1 = k + this.i.nextInt(16) + 8;
            k1 = this.i.nextInt(128);
            l1 = l + this.i.nextInt(16) + 8;
            (new WorldGenFlowers(Blocks.BROWN_MUSHROOM)).a(this.o, this.i, j1, k1, l1);
        }

        if (this.i.nextInt(1) == 0) {
            j1 = k + this.i.nextInt(16) + 8;
            k1 = this.i.nextInt(128);
            l1 = l + this.i.nextInt(16) + 8;
            (new WorldGenFlowers(Blocks.RED_MUSHROOM)).a(this.o, this.i, j1, k1, l1);
        }

        WorldGenMinable worldgenminable = new WorldGenMinable(Blocks.QUARTZ_ORE, 13, Blocks.NETHERRACK);

        int j2;

        for (k1 = 0; k1 < 16; ++k1) {
            l1 = k + this.i.nextInt(16);
            i2 = this.i.nextInt(108) + 10;
            j2 = l + this.i.nextInt(16);
            worldgenminable.a(this.o, this.i, l1, i2, j2);
        }

        for (k1 = 0; k1 < 16; ++k1) {
            l1 = k + this.i.nextInt(16);
            i2 = this.i.nextInt(108) + 10;
            j2 = l + this.i.nextInt(16);
            (new WorldGenHellLava(Blocks.LAVA, true)).a(this.o, this.i, l1, i2, j2);
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
        return "HellRandomLevelSource";
    }

    public List getMobsFor(EnumCreatureType enumcreaturetype, int i, int j, int k) {
        if (enumcreaturetype == EnumCreatureType.MONSTER) {
            if (this.c.b(i, j, k)) {
                return this.c.b();
            }

            if (this.c.d(i, j, k) && this.o.getType(i, j - 1, k) == Blocks.NETHER_BRICK) {
                return this.c.b();
            }
        }

        BiomeBase biomebase = this.o.getBiome(i, k);

        return biomebase.getMobs(enumcreaturetype);
    }

    public ChunkPosition findNearestMapFeature(World world, String s, int i, int j, int k) {
        return null;
    }

    public int getLoadedChunks() {
        return 0;
    }

    public void recreateStructures(int i, int j) {
        this.c.a(this, this.o, i, j, (Block[]) null);
    }
}

package net.minecraft.server;

import java.util.Random;

public class ChunkProviderSky implements IChunkProvider {

    private Random j;
    private NoiseGeneratorOctaves k;
    private NoiseGeneratorOctaves l;
    private NoiseGeneratorOctaves m;
    private NoiseGeneratorOctaves n;
    private NoiseGeneratorOctaves o;
    public NoiseGeneratorOctaves a;
    public NoiseGeneratorOctaves b;
    public NoiseGeneratorOctaves c;
    private World p;
    private double[] q;
    private double[] r = new double[256];
    private double[] s = new double[256];
    private double[] t = new double[256];
    private MapGenBase u = new MapGenCaves();
    private BiomeBase[] v;
    double[] d;
    double[] e;
    double[] f;
    double[] g;
    double[] h;
    int[][] i = new int[32][32];

    public ChunkProviderSky(World world, long i) {
        this.p = world;
        this.j = new Random(i);
        this.k = new NoiseGeneratorOctaves(this.j, 16);
        this.l = new NoiseGeneratorOctaves(this.j, 16);
        this.m = new NoiseGeneratorOctaves(this.j, 8);
        this.n = new NoiseGeneratorOctaves(this.j, 4);
        this.o = new NoiseGeneratorOctaves(this.j, 4);
        this.a = new NoiseGeneratorOctaves(this.j, 10);
        this.b = new NoiseGeneratorOctaves(this.j, 16);
        this.c = new NoiseGeneratorOctaves(this.j, 8);
    }

    public void a(int i, int j, byte[] abyte, BiomeBase[] abiomebase) {
        byte b0 = 2;
        int k = b0 + 1;

        int l = 128 / 4 + 1;
        int i1 = b0 + 1;

        this.q = this.a(this.q, i * b0, 0, j * b0, k, l, i1);

        for (int j1 = 0; j1 < b0; ++j1) {
            int k1 = 0;

            while (k1 < b0) {
                int l1 = 0;

                while (true) {

                    if (l1 >= 128 / 4) {
                        ++k1;
                        break;
                    }

                    double d0 = 0.25D;
                    double d1 = this.q[((j1 + 0) * i1 + k1 + 0) * l + l1 + 0];
                    double d2 = this.q[((j1 + 0) * i1 + k1 + 1) * l + l1 + 0];
                    double d3 = this.q[((j1 + 1) * i1 + k1 + 0) * l + l1 + 0];
                    double d4 = this.q[((j1 + 1) * i1 + k1 + 1) * l + l1 + 0];
                    double d5 = (this.q[((j1 + 0) * i1 + k1 + 0) * l + l1 + 1] - d1) * d0;
                    double d6 = (this.q[((j1 + 0) * i1 + k1 + 1) * l + l1 + 1] - d2) * d0;
                    double d7 = (this.q[((j1 + 1) * i1 + k1 + 0) * l + l1 + 1] - d3) * d0;
                    double d8 = (this.q[((j1 + 1) * i1 + k1 + 1) * l + l1 + 1] - d4) * d0;

                    for (int i2 = 0; i2 < 4; ++i2) {
                        double d9 = 0.125D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int j2 = 0; j2 < 8; ++j2) {
                            int k2 = j2 + j1 * 8;

                            k2 <<= 11;
                            int l2 = 0 + k1 * 8;

                            int i3 = k2 | l2 << 7 | l1 * 4 + i2;

                            int j3 = 1 << 7;
                            double d14 = 0.125D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int k3 = 0; k3 < 8; ++k3) {
                                int l3 = 0;

                                if (d15 > 0.0D) {
                                    l3 = Block.STONE.id;
                                }

                                abyte[i3] = (byte) l3;
                                i3 += j3;
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

                    ++l1;
                }
            }
        }
    }

    public void b(int i, int j, byte[] abyte, BiomeBase[] abiomebase) {
        double d0 = 0.03125D;

        this.r = this.n.a(this.r, i * 16, j * 16, 0, 16, 16, 1, d0, d0, 1.0D);
        this.s = this.n.a(this.s, i * 16, 109, j * 16, 16, 1, 16, d0, 1.0D, d0);
        this.t = this.o.a(this.t, i * 16, j * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                BiomeBase biomebase = abiomebase[k + l * 16];
                int i1 = (int) (this.t[k + l * 16] / 3.0D + 3.0D + this.j.nextDouble() * 0.25D);
                int j1 = -1;
                byte b0 = biomebase.n;
                byte b1 = biomebase.o;

                for (int k1 = 127; k1 >= 0; --k1) {
                    int l1 = l * 16 + k;

                    int i2 = l1 * 128 + k1;
                    byte b2 = abyte[i2];

                    if (b2 == 0) {
                        j1 = -1;
                    } else if (b2 == Block.STONE.id) {
                        if (j1 == -1) {
                            if (i1 <= 0) {
                                b0 = 0;
                                b1 = (byte) Block.STONE.id;
                            }

                            j1 = i1;
                            if (k1 >= 0) {
                                abyte[i2] = b0;
                            } else {
                                abyte[i2] = b1;
                            }
                        } else if (j1 > 0) {
                            --j1;
                            abyte[i2] = b1;
                            if (j1 == 0 && b1 == Block.SAND.id) {
                                j1 = this.j.nextInt(4);
                                b1 = (byte) Block.SANDSTONE.id;
                            }
                        }
                    }
                }
            }
        }
    }

    public Chunk getChunkAt(int i, int j) {
        return this.getOrCreateChunk(i, j);
    }

    public Chunk getOrCreateChunk(int i, int j) {
        this.j.setSeed((long) i * 341873128712L + (long) j * 132897987541L);

        byte[] abyte = new byte[16 * 128 * 16];
        Chunk chunk = new Chunk(this.p, abyte, i, j);

        this.v = this.p.getWorldChunkManager().a(this.v, i * 16, j * 16, 16, 16);
        this.a(i, j, abyte, this.v);
        this.b(i, j, abyte, this.v);
        this.u.a(this, this.p, i, j, abyte);
        chunk.initLighting();
        return chunk;
    }

    private double[] a(double[] adouble, int i, int j, int k, int l, int i1, int j1) {
        if (adouble == null) {
            adouble = new double[l * i1 * j1];
        }

        double d0 = 684.412D;
        double d1 = 684.412D;

        this.g = this.a.a(this.g, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        this.h = this.b.a(this.h, i, k, l, j1, 200.0D, 200.0D, 0.5D);
        d0 *= 2.0D;
        this.d = this.m.a(this.d, i, j, k, l, i1, j1, d0 / 80.0D, d1 / 160.0D, d0 / 80.0D);
        this.e = this.k.a(this.e, i, j, k, l, i1, j1, d0, d1, d0);
        this.f = this.l.a(this.f, i, j, k, l, i1, j1, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;

        for (int i2 = 0; i2 < l; ++i2) {
            for (int j2 = 0; j2 < j1; ++j2) {
                double d2 = (this.g[l1] + 256.0D) / 512.0D;

                if (d2 > 1.0D) {
                    d2 = 1.0D;
                }

                double d3 = this.h[l1] / 8000.0D;

                if (d3 < 0.0D) {
                    d3 = -d3 * 0.3D;
                }

                d3 = d3 * 3.0D - 2.0D;
                if (d3 > 1.0D) {
                    d3 = 1.0D;
                }

                d3 /= 8.0D;
                d3 = 0.0D;
                if (d2 < 0.0D) {
                    d2 = 0.0D;
                }

                d2 += 0.5D;
                d3 = d3 * (double) i1 / 16.0D;
                ++l1;
                double d4 = (double) i1 / 2.0D;

                for (int k2 = 0; k2 < i1; ++k2) {
                    double d5 = 0.0D;
                    double d6 = ((double) k2 - d4) * 8.0D / d2;

                    if (d6 < 0.0D) {
                        d6 *= -1.0D;
                    }

                    double d7 = this.e[k1] / 512.0D;
                    double d8 = this.f[k1] / 512.0D;
                    double d9 = (this.d[k1] / 10.0D + 1.0D) / 2.0D;

                    if (d9 < 0.0D) {
                        d5 = d7;
                    } else if (d9 > 1.0D) {
                        d5 = d8;
                    } else {
                        d5 = d7 + (d8 - d7) * d9;
                    }

                    d5 -= 8.0D;
                    byte b0 = 32;
                    double d10;

                    if (k2 > i1 - b0) {
                        d10 = (double) ((float) (k2 - (i1 - b0)) / ((float) b0 - 1.0F));
                        d5 = d5 * (1.0D - d10) + -30.0D * d10;
                    }

                    b0 = 8;
                    if (k2 < b0) {
                        d10 = (double) ((float) (b0 - k2) / ((float) b0 - 1.0F));
                        d5 = d5 * (1.0D - d10) + -30.0D * d10;
                    }

                    adouble[k1] = d5;
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
        BlockSand.instaFall = true;
        int k = i * 16;
        int l = j * 16;
        BiomeBase biomebase = this.p.getWorldChunkManager().getBiome(k + 16, l + 16);

        this.j.setSeed(this.p.getSeed());
        long i1 = this.j.nextLong() / 2L * 2L + 1L;
        long j1 = this.j.nextLong() / 2L * 2L + 1L;

        this.j.setSeed((long) i * i1 + (long) j * j1 ^ this.p.getSeed());
        double d0 = 0.25D;
        Random random;
        int k1;
        int l1;
        int i2;

        if (this.j.nextInt(4) == 0) {
            k1 = k + this.j.nextInt(16) + 8;
            random = this.j;

            l1 = random.nextInt(128);
            i2 = l + this.j.nextInt(16) + 8;
            (new WorldGenLakes(Block.STATIONARY_WATER.id)).a(this.p, this.j, k1, l1, i2);
        }

        Random random1;

        if (this.j.nextInt(8) == 0) {
            k1 = k + this.j.nextInt(16) + 8;
            random = this.j;
            random1 = this.j;

            l1 = random.nextInt(random1.nextInt(128 - 8) + 8);
            i2 = l + this.j.nextInt(16) + 8;
            if (l1 < 64 || this.j.nextInt(10) == 0) {
                (new WorldGenLakes(Block.STATIONARY_LAVA.id)).a(this.p, this.j, k1, l1, i2);
            }
        }

        int j2;

        for (k1 = 0; k1 < 8; ++k1) {
            l1 = k + this.j.nextInt(16) + 8;
            random = this.j;

            i2 = random.nextInt(128);
            j2 = l + this.j.nextInt(16) + 8;
            (new WorldGenDungeons()).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 10; ++k1) {
            l1 = k + this.j.nextInt(16);
            random = this.j;

            i2 = random.nextInt(128);
            j2 = l + this.j.nextInt(16);
            (new WorldGenClay(32)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 20; ++k1) {
            l1 = k + this.j.nextInt(16);
            random = this.j;

            i2 = random.nextInt(128);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.DIRT.id, 32)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 10; ++k1) {
            l1 = k + this.j.nextInt(16);
            random = this.j;

            i2 = random.nextInt(128);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.GRAVEL.id, 32)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 20; ++k1) {
            l1 = k + this.j.nextInt(16);
            random = this.j;

            i2 = random.nextInt(128);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.COAL_ORE.id, 16)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 20; ++k1) {
            l1 = k + this.j.nextInt(16);
            random = this.j;

            i2 = random.nextInt(128 / 2);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.IRON_ORE.id, 8)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 2; ++k1) {
            l1 = k + this.j.nextInt(16);
            random = this.j;

            i2 = random.nextInt(128 / 4);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.GOLD_ORE.id, 8)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 8; ++k1) {
            l1 = k + this.j.nextInt(16);
            random = this.j;

            i2 = random.nextInt(128 / 8);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.REDSTONE_ORE.id, 7)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 1; ++k1) {
            l1 = k + this.j.nextInt(16);
            random = this.j;

            i2 = random.nextInt(128 / 8);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.DIAMOND_ORE.id, 7)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 1; ++k1) {
            l1 = k + this.j.nextInt(16);
            random = this.j;

            int k2 = random.nextInt(128 / 8);

            random1 = this.j;

            i2 = k2 + random1.nextInt(128 / 8);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.LAPIS_ORE.id, 6)).a(this.p, this.j, l1, i2, j2);
        }

        d0 = 0.5D;
        k1 = (int) ((this.c.a((double) k * d0, (double) l * d0) / 8.0D + this.j.nextDouble() * 4.0D + 4.0D) / 3.0D);
        l1 = 0;
        if (this.j.nextInt(10) == 0) {
            ++l1;
        }

        if (biomebase == BiomeBase.FOREST) {
            l1 += k1 + 5;
        }

        if (biomebase == BiomeBase.DESERT) {
            l1 -= 20;
        }

        if (biomebase == BiomeBase.PLAINS) {
            l1 -= 20;
        }

        int l2;

        for (i2 = 0; i2 < l1; ++i2) {
            j2 = k + this.j.nextInt(16) + 8;
            l2 = l + this.j.nextInt(16) + 8;
            WorldGenerator worldgenerator = biomebase.a(this.j);

            worldgenerator.a(1.0D, 1.0D, 1.0D);
            worldgenerator.a(this.p, this.j, j2, this.p.getHighestBlockYAt(j2, l2), l2);
        }

        int i3;

        for (i2 = 0; i2 < 2; ++i2) {
            j2 = k + this.j.nextInt(16) + 8;
            random = this.j;

            l2 = random.nextInt(128);
            i3 = l + this.j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.YELLOW_FLOWER.id)).a(this.p, this.j, j2, l2, i3);
        }

        if (this.j.nextInt(2) == 0) {
            i2 = k + this.j.nextInt(16) + 8;
            random = this.j;

            j2 = random.nextInt(128);
            l2 = l + this.j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.RED_ROSE.id)).a(this.p, this.j, i2, j2, l2);
        }

        if (this.j.nextInt(4) == 0) {
            i2 = k + this.j.nextInt(16) + 8;
            random = this.j;

            j2 = random.nextInt(128);
            l2 = l + this.j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.BROWN_MUSHROOM.id)).a(this.p, this.j, i2, j2, l2);
        }

        if (this.j.nextInt(8) == 0) {
            i2 = k + this.j.nextInt(16) + 8;
            random = this.j;

            j2 = random.nextInt(128);
            l2 = l + this.j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.RED_MUSHROOM.id)).a(this.p, this.j, i2, j2, l2);
        }

        for (i2 = 0; i2 < 10; ++i2) {
            j2 = k + this.j.nextInt(16) + 8;
            random = this.j;

            l2 = random.nextInt(128);
            i3 = l + this.j.nextInt(16) + 8;
            (new WorldGenReed()).a(this.p, this.j, j2, l2, i3);
        }

        if (this.j.nextInt(32) == 0) {
            i2 = k + this.j.nextInt(16) + 8;
            random = this.j;

            j2 = random.nextInt(128);
            l2 = l + this.j.nextInt(16) + 8;
            (new WorldGenPumpkin()).a(this.p, this.j, i2, j2, l2);
        }

        i2 = 0;
        if (biomebase == BiomeBase.DESERT) {
            i2 += 10;
        }

        int j3;

        for (j2 = 0; j2 < i2; ++j2) {
            l2 = k + this.j.nextInt(16) + 8;
            random = this.j;

            i3 = random.nextInt(128);
            j3 = l + this.j.nextInt(16) + 8;
            (new WorldGenCactus()).a(this.p, this.j, l2, i3, j3);
        }

        for (j2 = 0; j2 < 50; ++j2) {
            l2 = k + this.j.nextInt(16) + 8;
            random = this.j;
            random1 = this.j;

            i3 = random.nextInt(random1.nextInt(128 - 8) + 8);
            j3 = l + this.j.nextInt(16) + 8;
            (new WorldGenLiquids(Block.WATER.id)).a(this.p, this.j, l2, i3, j3);
        }

        for (j2 = 0; j2 < 20; ++j2) {
            l2 = k + this.j.nextInt(16) + 8;
            random = this.j;
            random1 = this.j;
            Random random2 = this.j;

            i3 = random.nextInt(random1.nextInt(random2.nextInt(128 - 16) + 8) + 8);
            j3 = l + this.j.nextInt(16) + 8;
            (new WorldGenLiquids(Block.LAVA.id)).a(this.p, this.j, l2, i3, j3);
        }

        BlockSand.instaFall = false;
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
        return true;
    }

    public boolean unloadChunks() {
        return false;
    }

    public boolean canSave() {
        return true;
    }
}

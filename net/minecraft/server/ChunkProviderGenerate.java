package net.minecraft.server;

import java.util.Random;

public class ChunkProviderGenerate implements IChunkProvider {

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
    double[] d;
    double[] e;
    double[] f;
    double[] g;
    double[] h;
    int[][] i = new int[32][32];

    public ChunkProviderGenerate(World world, long i) {
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

    public void a(int i, int j, byte[] abyte) {
        byte b0 = 4;
        byte b1 = 64;
        int k = b0 + 1;
        byte b2 = 17;
        int l = b0 + 1;

        this.q = this.a(this.q, i * b0, 0, j * b0, k, b2, l);

        for (int i1 = 0; i1 < b0; ++i1) {
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k1 = 0; k1 < 16; ++k1) {
                    double d0 = 0.125D;
                    double d1 = this.q[((i1 + 0) * l + j1 + 0) * b2 + k1 + 0];
                    double d2 = this.q[((i1 + 0) * l + j1 + 1) * b2 + k1 + 0];
                    double d3 = this.q[((i1 + 1) * l + j1 + 0) * b2 + k1 + 0];
                    double d4 = this.q[((i1 + 1) * l + j1 + 1) * b2 + k1 + 0];
                    double d5 = (this.q[((i1 + 0) * l + j1 + 0) * b2 + k1 + 1] - d1) * d0;
                    double d6 = (this.q[((i1 + 0) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
                    double d7 = (this.q[((i1 + 1) * l + j1 + 0) * b2 + k1 + 1] - d3) * d0;
                    double d8 = (this.q[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4) * d0;

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
                                int l2 = 0;

                                if (k1 * 8 + l1 < b1) {
                                    if (this.p.d && k1 * 8 + l1 >= b1 - 1) {
                                        l2 = Block.ICE.bc;
                                    } else {
                                        l2 = Block.STATIONARY_WATER.bc;
                                    }
                                }

                                if (d15 > 0.0D) {
                                    l2 = Block.STONE.bc;
                                }

                                abyte[j2] = (byte) l2;
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

    public void b(int i, int j, byte[] abyte) {
        byte b0 = 64;
        double d0 = 0.03125D;

        this.r = this.n.a(this.r, (double) (i * 16), (double) (j * 16), 0.0D, 16, 16, 1, d0, d0, 1.0D);
        this.s = this.n.a(this.s, (double) (j * 16), 109.0134D, (double) (i * 16), 16, 1, 16, d0, 1.0D, d0);
        this.t = this.o.a(this.t, (double) (i * 16), (double) (j * 16), 0.0D, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                boolean flag = this.r[k + l * 16] + this.j.nextDouble() * 0.2D > 0.0D;
                boolean flag1 = this.s[k + l * 16] + this.j.nextDouble() * 0.2D > 3.0D;
                int i1 = (int) (this.t[k + l * 16] / 3.0D + 3.0D + this.j.nextDouble() * 0.25D);
                int j1 = -1;
                byte b1 = (byte) Block.GRASS.bc;
                byte b2 = (byte) Block.DIRT.bc;

                for (int k1 = 127; k1 >= 0; --k1) {
                    int l1 = (k * 16 + l) * 128 + k1;

                    if (k1 <= 0 + this.j.nextInt(6) - 1) {
                        abyte[l1] = (byte) Block.BEDROCK.bc;
                    } else {
                        byte b3 = abyte[l1];

                        if (b3 == 0) {
                            j1 = -1;
                        } else if (b3 == Block.STONE.bc) {
                            if (j1 == -1) {
                                if (i1 <= 0) {
                                    b1 = 0;
                                    b2 = (byte) Block.STONE.bc;
                                } else if (k1 >= b0 - 4 && k1 <= b0 + 1) {
                                    b1 = (byte) Block.GRASS.bc;
                                    b2 = (byte) Block.DIRT.bc;
                                    if (flag1) {
                                        b1 = 0;
                                    }

                                    if (flag1) {
                                        b2 = (byte) Block.GRAVEL.bc;
                                    }

                                    if (flag) {
                                        b1 = (byte) Block.SAND.bc;
                                    }

                                    if (flag) {
                                        b2 = (byte) Block.SAND.bc;
                                    }
                                }

                                if (k1 < b0 && b1 == 0) {
                                    b1 = (byte) Block.STATIONARY_WATER.bc;
                                }

                                j1 = i1;
                                if (k1 >= b0 - 1) {
                                    abyte[l1] = b1;
                                } else {
                                    abyte[l1] = b2;
                                }
                            } else if (j1 > 0) {
                                --j1;
                                abyte[l1] = b2;
                            }
                        }
                    }
                }
            }
        }
    }

    public Chunk b(int i, int j) {
        this.j.setSeed((long) i * 341873128712L + (long) j * 132897987541L);
        byte[] abyte = new byte['\u8000'];
        Chunk chunk = new Chunk(this.p, abyte, i, j);

        this.a(i, j, abyte);
        this.b(i, j, abyte);
        this.u.a(this, this.p, i, j, abyte);
        chunk.b();
        return chunk;
    }

    private double[] a(double[] adouble, int i, int j, int k, int l, int i1, int j1) {
        if (adouble == null) {
            adouble = new double[l * i1 * j1];
        }

        double d0 = 684.412D;
        double d1 = 684.412D;

        this.g = this.a.a(this.g, (double) i, (double) j, (double) k, l, 1, j1, 1.0D, 0.0D, 1.0D);
        this.h = this.b.a(this.h, (double) i, (double) j, (double) k, l, 1, j1, 100.0D, 0.0D, 100.0D);
        this.d = this.m.a(this.d, (double) i, (double) j, (double) k, l, i1, j1, d0 / 80.0D, d1 / 160.0D, d0 / 80.0D);
        this.e = this.k.a(this.e, (double) i, (double) j, (double) k, l, i1, j1, d0, d1, d0);
        this.f = this.l.a(this.f, (double) i, (double) j, (double) k, l, i1, j1, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;

        for (int i2 = 0; i2 < l; ++i2) {
            for (int j2 = 0; j2 < j1; ++j2) {
                double d2 = (this.g[l1] + 256.0D) / 512.0D;

                if (d2 > 1.0D) {
                    d2 = 1.0D;
                }

                double d3 = 0.0D;
                double d4 = this.h[l1] / 8000.0D;

                if (d4 < 0.0D) {
                    d4 = -d4;
                }

                d4 = d4 * 3.0D - 3.0D;
                if (d4 < 0.0D) {
                    d4 /= 2.0D;
                    if (d4 < -1.0D) {
                        d4 = -1.0D;
                    }

                    d4 /= 1.4D;
                    d4 /= 2.0D;
                    d2 = 0.0D;
                } else {
                    if (d4 > 1.0D) {
                        d4 = 1.0D;
                    }

                    d4 /= 6.0D;
                }

                d2 += 0.5D;
                d4 = d4 * (double) i1 / 16.0D;
                double d5 = (double) i1 / 2.0D + d4 * 4.0D;

                ++l1;

                for (int k2 = 0; k2 < i1; ++k2) {
                    double d6 = 0.0D;
                    double d7 = ((double) k2 - d5) * 12.0D / d2;

                    if (d7 < 0.0D) {
                        d7 *= 4.0D;
                    }

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

                    if ((double) k2 < d3) {
                        d11 = (d3 - (double) k2) / 4.0D;
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

    public boolean a(int i, int j) {
        return true;
    }

    public void a(IChunkProvider ichunkprovider, int i, int j) {
        BlockSand.a = true;
        int k = i * 16;
        int l = j * 16;

        this.j.setSeed(this.p.t);
        long i1 = this.j.nextLong() / 2L * 2L + 1L;
        long j1 = this.j.nextLong() / 2L * 2L + 1L;

        this.j.setSeed((long) i * i1 + (long) j * j1 ^ this.p.t);
        double d0 = 0.25D;

        int k1;
        int l1;
        int i2;
        int j2;

        for (k1 = 0; k1 < 8; ++k1) {
            l1 = k + this.j.nextInt(16) + 8;
            i2 = this.j.nextInt(128);
            j2 = l + this.j.nextInt(16) + 8;
            (new WorldGenDungeons()).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 10; ++k1) {
            l1 = k + this.j.nextInt(16);
            i2 = this.j.nextInt(128);
            j2 = l + this.j.nextInt(16);
            (new WorldGenClay(32)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 20; ++k1) {
            l1 = k + this.j.nextInt(16);
            i2 = this.j.nextInt(128);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.DIRT.bc, 32)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 10; ++k1) {
            l1 = k + this.j.nextInt(16);
            i2 = this.j.nextInt(128);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.GRAVEL.bc, 32)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 20; ++k1) {
            l1 = k + this.j.nextInt(16);
            i2 = this.j.nextInt(128);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.COAL_ORE.bc, 16)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 20; ++k1) {
            l1 = k + this.j.nextInt(16);
            i2 = this.j.nextInt(64);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.IRON_ORE.bc, 8)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 2; ++k1) {
            l1 = k + this.j.nextInt(16);
            i2 = this.j.nextInt(32);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.GOLD_ORE.bc, 8)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 8; ++k1) {
            l1 = k + this.j.nextInt(16);
            i2 = this.j.nextInt(16);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.REDSTONE_ORE.bc, 7)).a(this.p, this.j, l1, i2, j2);
        }

        for (k1 = 0; k1 < 1; ++k1) {
            l1 = k + this.j.nextInt(16);
            i2 = this.j.nextInt(16);
            j2 = l + this.j.nextInt(16);
            (new WorldGenMinable(Block.DIAMOND_ORE.bc, 7)).a(this.p, this.j, l1, i2, j2);
        }

        d0 = 0.5D;
        k1 = (int) ((this.c.a((double) k * d0, (double) l * d0) / 8.0D + this.j.nextDouble() * 4.0D + 4.0D) / 3.0D);
        if (k1 < 0) {
            k1 = 0;
        }

        if (this.j.nextInt(10) == 0) {
            ++k1;
        }

        Object object = new WorldGenTrees();

        if (this.j.nextInt(10) == 0) {
            object = new WorldGenBigTree();
        }

        int k2;

        for (i2 = 0; i2 < k1; ++i2) {
            j2 = k + this.j.nextInt(16) + 8;
            k2 = l + this.j.nextInt(16) + 8;
            ((WorldGenerator) object).a(1.0D, 1.0D, 1.0D);
            ((WorldGenerator) object).a(this.p, this.j, j2, this.p.c(j2, k2), k2);
        }

        int l2;

        for (i2 = 0; i2 < 2; ++i2) {
            j2 = k + this.j.nextInt(16) + 8;
            k2 = this.j.nextInt(128);
            l2 = l + this.j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.YELLOW_FLOWER.bc)).a(this.p, this.j, j2, k2, l2);
        }

        if (this.j.nextInt(2) == 0) {
            i2 = k + this.j.nextInt(16) + 8;
            j2 = this.j.nextInt(128);
            k2 = l + this.j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.RED_ROSE.bc)).a(this.p, this.j, i2, j2, k2);
        }

        if (this.j.nextInt(4) == 0) {
            i2 = k + this.j.nextInt(16) + 8;
            j2 = this.j.nextInt(128);
            k2 = l + this.j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.BROWN_MUSHROOM.bc)).a(this.p, this.j, i2, j2, k2);
        }

        if (this.j.nextInt(8) == 0) {
            i2 = k + this.j.nextInt(16) + 8;
            j2 = this.j.nextInt(128);
            k2 = l + this.j.nextInt(16) + 8;
            (new WorldGenFlowers(Block.RED_MUSHROOM.bc)).a(this.p, this.j, i2, j2, k2);
        }

        for (i2 = 0; i2 < 10; ++i2) {
            j2 = k + this.j.nextInt(16) + 8;
            k2 = this.j.nextInt(128);
            l2 = l + this.j.nextInt(16) + 8;
            (new WorldGenReed()).a(this.p, this.j, j2, k2, l2);
        }

        for (i2 = 0; i2 < 1; ++i2) {
            j2 = k + this.j.nextInt(16) + 8;
            k2 = this.j.nextInt(128);
            l2 = l + this.j.nextInt(16) + 8;
            (new WorldGenCactus()).a(this.p, this.j, j2, k2, l2);
        }

        for (i2 = 0; i2 < 50; ++i2) {
            j2 = k + this.j.nextInt(16) + 8;
            k2 = this.j.nextInt(this.j.nextInt(120) + 8);
            l2 = l + this.j.nextInt(16) + 8;
            (new WorldGenLiquids(Block.WATER.bc)).a(this.p, this.j, j2, k2, l2);
        }

        for (i2 = 0; i2 < 20; ++i2) {
            j2 = k + this.j.nextInt(16) + 8;
            k2 = this.j.nextInt(this.j.nextInt(this.j.nextInt(112) + 8) + 8);
            l2 = l + this.j.nextInt(16) + 8;
            (new WorldGenLiquids(Block.LAVA.bc)).a(this.p, this.j, j2, k2, l2);
        }

        for (i2 = k + 8 + 0; i2 < k + 8 + 16; ++i2) {
            for (j2 = l + 8 + 0; j2 < l + 8 + 16; ++j2) {
                k2 = this.p.d(i2, j2);
                if (this.p.d && k2 > 0 && k2 < 128 && this.p.a(i2, k2, j2) == 0 && this.p.c(i2, k2 - 1, j2).c() && this.p.c(i2, k2 - 1, j2) != Material.r) {
                    this.p.d(i2, k2, j2, Block.SNOW.bc);
                }
            }
        }

        BlockSand.a = false;
    }

    public boolean a(boolean flag, IProgressUpdate iprogressupdate) {
        return true;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }
}

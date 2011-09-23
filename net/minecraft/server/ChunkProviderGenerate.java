package net.minecraft.server;

import java.util.Random;

public class ChunkProviderGenerate implements IChunkProvider {

    private Random n;
    private NoiseGeneratorOctaves o;
    private NoiseGeneratorOctaves p;
    private NoiseGeneratorOctaves q;
    private NoiseGeneratorOctaves r;
    public NoiseGeneratorOctaves a;
    public NoiseGeneratorOctaves b;
    public NoiseGeneratorOctaves c;
    private World s;
    private final boolean t;
    private double[] u;
    private double[] v = new double[256];
    private MapGenBase w = new MapGenCaves();
    public WorldGenStronghold d = new WorldGenStronghold();
    public WorldGenVillage e = new WorldGenVillage();
    public WorldGenMineshaft f = new WorldGenMineshaft();
    private MapGenBase x = new WorldGenCanyon();
    private BiomeBase[] y;
    double[] g;
    double[] h;
    double[] i;
    double[] j;
    double[] k;
    float[] l;
    int[][] m = new int[32][32];

    public ChunkProviderGenerate(World world, long i, boolean flag) {
        this.s = world;
        this.t = flag;
        this.n = new Random(i);
        this.o = new NoiseGeneratorOctaves(this.n, 16);
        this.p = new NoiseGeneratorOctaves(this.n, 16);
        this.q = new NoiseGeneratorOctaves(this.n, 8);
        this.r = new NoiseGeneratorOctaves(this.n, 4);
        this.a = new NoiseGeneratorOctaves(this.n, 10);
        this.b = new NoiseGeneratorOctaves(this.n, 16);
        this.c = new NoiseGeneratorOctaves(this.n, 8);
    }

    public void a(int i, int j, byte[] abyte) {
        byte b0 = 4;

        int k = 128 / 8;

        byte b1 = 63;
        int l = b0 + 1;

        int i1 = 128 / 8 + 1;
        int j1 = b0 + 1;

        this.y = this.s.getWorldChunkManager().getBiomes(this.y, i * 4 - 2, j * 4 - 2, l + 5, j1 + 5);
        this.u = this.a(this.u, i * b0, 0, j * b0, l, i1, j1);

        for (int k1 = 0; k1 < b0; ++k1) {
            for (int l1 = 0; l1 < b0; ++l1) {
                for (int i2 = 0; i2 < k; ++i2) {
                    double d0 = 0.125D;
                    double d1 = this.u[((k1 + 0) * j1 + l1 + 0) * i1 + i2 + 0];
                    double d2 = this.u[((k1 + 0) * j1 + l1 + 1) * i1 + i2 + 0];
                    double d3 = this.u[((k1 + 1) * j1 + l1 + 0) * i1 + i2 + 0];
                    double d4 = this.u[((k1 + 1) * j1 + l1 + 1) * i1 + i2 + 0];
                    double d5 = (this.u[((k1 + 0) * j1 + l1 + 0) * i1 + i2 + 1] - d1) * d0;
                    double d6 = (this.u[((k1 + 0) * j1 + l1 + 1) * i1 + i2 + 1] - d2) * d0;
                    double d7 = (this.u[((k1 + 1) * j1 + l1 + 0) * i1 + i2 + 1] - d3) * d0;
                    double d8 = (this.u[((k1 + 1) * j1 + l1 + 1) * i1 + i2 + 1] - d4) * d0;

                    for (int j2 = 0; j2 < 8; ++j2) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int k2 = 0; k2 < 4; ++k2) {
                            int l2 = k2 + k1 * 4;

                            l2 <<= 11;
                            int i3 = 0 + l1 * 4;

                            int j3 = l2 | i3 << 7 | i2 * 8 + j2;

                            int k3 = 1 << 7;
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int l3 = 0; l3 < 4; ++l3) {
                                int i4 = 0;

                                if (i2 * 8 + j2 < b1) {
                                    i4 = Block.STATIONARY_WATER.id;
                                }

                                if (d15 > 0.0D) {
                                    i4 = Block.STONE.id;
                                }

                                abyte[j3] = (byte) i4;
                                j3 += k3;
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

    public void a(int i, int j, byte[] abyte, BiomeBase[] abiomebase) {

        byte b0 = 63;
        double d0 = 0.03125D;

        this.v = this.r.a(this.v, i * 16, j * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                BiomeBase biomebase = abiomebase[l + k * 16];
                int i1 = (int) (this.v[k + l * 16] / 3.0D + 3.0D + this.n.nextDouble() * 0.25D);
                int j1 = -1;
                byte b1 = biomebase.n;
                byte b2 = biomebase.o;

                for (int k1 = 127; k1 >= 0; --k1) {
                    int l1 = l * 16 + k;

                    int i2 = l1 * 128 + k1;

                    if (k1 <= 0 + this.n.nextInt(5)) {
                        abyte[i2] = (byte) Block.BEDROCK.id;
                    } else {
                        byte b3 = abyte[i2];

                        if (b3 == 0) {
                            j1 = -1;
                        } else if (b3 == Block.STONE.id) {
                            if (j1 == -1) {
                                if (i1 <= 0) {
                                    b1 = 0;
                                    b2 = (byte) Block.STONE.id;
                                } else if (k1 >= b0 - 4 && k1 <= b0 + 1) {
                                    b1 = biomebase.n;
                                    b2 = biomebase.o;
                                }

                                if (k1 < b0 && b1 == 0) {
                                    b1 = (byte) Block.STATIONARY_WATER.id;
                                }

                                j1 = i1;
                                if (k1 >= b0 - 1) {
                                    abyte[i2] = b1;
                                } else {
                                    abyte[i2] = b2;
                                }
                            } else if (j1 > 0) {
                                --j1;
                                abyte[i2] = b2;
                                if (j1 == 0 && b2 == Block.SAND.id) {
                                    j1 = this.n.nextInt(4);
                                    b2 = (byte) Block.SANDSTONE.id;
                                }
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
        this.n.setSeed((long) i * 341873128712L + (long) j * 132897987541L);

        byte[] abyte = new byte[16 * 128 * 16];
        Chunk chunk = new Chunk(this.s, abyte, i, j);

        this.a(i, j, abyte);
        this.y = this.s.getWorldChunkManager().a(this.y, i * 16, j * 16, 16, 16);
        this.a(i, j, abyte, this.y);
        this.w.a(this, this.s, i, j, abyte);
        if (this.t) {
            this.d.a(this, this.s, i, j, abyte);
            this.f.a(this, this.s, i, j, abyte);
            this.e.a(this, this.s, i, j, abyte);
        }

        this.x.a(this, this.s, i, j, abyte);
        chunk.initLighting();
        return chunk;
    }

    private double[] a(double[] adouble, int i, int j, int k, int l, int i1, int j1) {
        if (adouble == null) {
            adouble = new double[l * i1 * j1];
        }

        if (this.l == null) {
            this.l = new float[25];

            for (int k1 = -2; k1 <= 2; ++k1) {
                for (int l1 = -2; l1 <= 2; ++l1) {
                    float f = 10.0F / MathHelper.c((float) (k1 * k1 + l1 * l1) + 0.2F);

                    this.l[k1 + 2 + (l1 + 2) * 5] = f;
                }
            }
        }

        double d0 = 684.412D;
        double d1 = 684.412D;

        this.j = this.a.a(this.j, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        this.k = this.b.a(this.k, i, k, l, j1, 200.0D, 200.0D, 0.5D);
        this.g = this.q.a(this.g, i, j, k, l, i1, j1, d0 / 80.0D, d1 / 160.0D, d0 / 80.0D);
        this.h = this.o.a(this.h, i, j, k, l, i1, j1, d0, d1, d0);
        this.i = this.p.a(this.i, i, j, k, l, i1, j1, d0, d1, d0);
        boolean flag = false;
        boolean flag1 = false;
        int i2 = 0;
        int j2 = 0;

        for (int k2 = 0; k2 < l; ++k2) {
            for (int l2 = 0; l2 < j1; ++l2) {
                float f1 = 0.0F;
                float f2 = 0.0F;
                float f3 = 0.0F;
                byte b0 = 2;
                BiomeBase biomebase = this.y[k2 + 2 + (l2 + 2) * (l + 5)];

                for (int i3 = -b0; i3 <= b0; ++i3) {
                    for (int j3 = -b0; j3 <= b0; ++j3) {
                        BiomeBase biomebase1 = this.y[k2 + i3 + 2 + (l2 + j3 + 2) * (l + 5)];
                        float f4 = this.l[i3 + 2 + (j3 + 2) * 5] / (biomebase1.q + 2.0F);

                        if (biomebase1.q > biomebase.q) {
                            f4 /= 2.0F;
                        }

                        f1 += biomebase1.r * f4;
                        f2 += biomebase1.q * f4;
                        f3 += f4;
                    }
                }

                f1 /= f3;
                f2 /= f3;
                f1 = f1 * 0.9F + 0.1F;
                f2 = (f2 * 4.0F - 1.0F) / 8.0F;
                double d2 = this.k[j2] / 8000.0D;

                if (d2 < 0.0D) {
                    d2 = -d2 * 0.3D;
                }

                d2 = d2 * 3.0D - 2.0D;
                if (d2 < 0.0D) {
                    d2 /= 2.0D;
                    if (d2 < -1.0D) {
                        d2 = -1.0D;
                    }

                    d2 /= 1.4D;
                    d2 /= 2.0D;
                } else {
                    if (d2 > 1.0D) {
                        d2 = 1.0D;
                    }

                    d2 /= 8.0D;
                }

                ++j2;

                for (int k3 = 0; k3 < i1; ++k3) {
                    double d3 = (double) f2;
                    double d4 = (double) f1;

                    d3 += d2 * 0.2D;
                    d3 = d3 * (double) i1 / 16.0D;
                    double d5 = (double) i1 / 2.0D + d3 * 4.0D;
                    double d6 = 0.0D;
                    double d7 = ((double) k3 - d5) * 12.0D * 128.0D;

                    double d8 = d7 / 128.0D / d4;

                    if (d8 < 0.0D) {
                        d8 *= 4.0D;
                    }

                    double d9 = this.h[i2] / 512.0D;
                    double d10 = this.i[i2] / 512.0D;
                    double d11 = (this.g[i2] / 10.0D + 1.0D) / 2.0D;

                    if (d11 < 0.0D) {
                        d6 = d9;
                    } else if (d11 > 1.0D) {
                        d6 = d10;
                    } else {
                        d6 = d9 + (d10 - d9) * d11;
                    }

                    d6 -= d8;
                    if (k3 > i1 - 4) {
                        double d12 = (double) ((float) (k3 - (i1 - 4)) / 3.0F);

                        d6 = d6 * (1.0D - d12) + -10.0D * d12;
                    }

                    adouble[i2] = d6;
                    ++i2;
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
        BiomeBase biomebase = this.s.getWorldChunkManager().getBiome(k + 16, l + 16);

        this.n.setSeed(this.s.getSeed());
        long i1 = this.n.nextLong() / 2L * 2L + 1L;
        long j1 = this.n.nextLong() / 2L * 2L + 1L;

        this.n.setSeed((long) i * i1 + (long) j * j1 ^ this.s.getSeed());
        boolean flag = false;

        if (this.t) {
            this.d.a(this.s, this.n, i, j);
            this.f.a(this.s, this.n, i, j);
            flag = this.e.a(this.s, this.n, i, j);
        }

        int k1;
        int l1;
        Random random;
        int i2;

        if (!flag && this.n.nextInt(4) == 0) {
            k1 = k + this.n.nextInt(16) + 8;
            random = this.n;

            l1 = random.nextInt(128);
            i2 = l + this.n.nextInt(16) + 8;
            (new WorldGenLakes(Block.STATIONARY_WATER.id)).a(this.s, this.n, k1, l1, i2);
        }

        if (!flag && this.n.nextInt(8) == 0) {
            k1 = k + this.n.nextInt(16) + 8;
            random = this.n;
            Random random1 = this.n;

            l1 = random.nextInt(random1.nextInt(128 - 8) + 8);
            i2 = l + this.n.nextInt(16) + 8;

            if (l1 < 63 || this.n.nextInt(10) == 0) {
                (new WorldGenLakes(Block.STATIONARY_LAVA.id)).a(this.s, this.n, k1, l1, i2);
            }
        }

        for (k1 = 0; k1 < 8; ++k1) {
            l1 = k + this.n.nextInt(16) + 8;
            random = this.n;

            i2 = random.nextInt(128);
            int j2 = l + this.n.nextInt(16) + 8;

            if ((new WorldGenDungeons()).a(this.s, this.n, l1, i2, j2)) {
                ;
            }
        }

        biomebase.a(this.s, this.n, k, l);
        SpawnerCreature.a(this.s, biomebase, k + 8, l + 8, 16, 16, this.n);
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

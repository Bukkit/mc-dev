package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class ChunkProviderTheEnd implements IChunkProvider {

    private Random i;
    private NoiseGeneratorOctaves j;
    private NoiseGeneratorOctaves k;
    private NoiseGeneratorOctaves l;
    public NoiseGeneratorOctaves a;
    public NoiseGeneratorOctaves b;
    private World m;
    private double[] n;
    private BiomeBase[] o;
    double[] c;
    double[] d;
    double[] e;
    double[] f;
    double[] g;
    int[][] h = new int[32][32];

    public ChunkProviderTheEnd(World world, long i) {
        this.m = world;
        this.i = new Random(i);
        this.j = new NoiseGeneratorOctaves(this.i, 16);
        this.k = new NoiseGeneratorOctaves(this.i, 16);
        this.l = new NoiseGeneratorOctaves(this.i, 8);
        this.a = new NoiseGeneratorOctaves(this.i, 10);
        this.b = new NoiseGeneratorOctaves(this.i, 16);
    }

    public void a(int i, int j, Block[] ablock, BiomeBase[] abiomebase) {
        byte b0 = 2;
        int k = b0 + 1;
        byte b1 = 33;
        int l = b0 + 1;

        this.n = this.a(this.n, i * b0, 0, j * b0, k, b1, l);

        for (int i1 = 0; i1 < b0; ++i1) {
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k1 = 0; k1 < 32; ++k1) {
                    double d0 = 0.25D;
                    double d1 = this.n[((i1 + 0) * l + j1 + 0) * b1 + k1 + 0];
                    double d2 = this.n[((i1 + 0) * l + j1 + 1) * b1 + k1 + 0];
                    double d3 = this.n[((i1 + 1) * l + j1 + 0) * b1 + k1 + 0];
                    double d4 = this.n[((i1 + 1) * l + j1 + 1) * b1 + k1 + 0];
                    double d5 = (this.n[((i1 + 0) * l + j1 + 0) * b1 + k1 + 1] - d1) * d0;
                    double d6 = (this.n[((i1 + 0) * l + j1 + 1) * b1 + k1 + 1] - d2) * d0;
                    double d7 = (this.n[((i1 + 1) * l + j1 + 0) * b1 + k1 + 1] - d3) * d0;
                    double d8 = (this.n[((i1 + 1) * l + j1 + 1) * b1 + k1 + 1] - d4) * d0;

                    for (int l1 = 0; l1 < 4; ++l1) {
                        double d9 = 0.125D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i2 = 0; i2 < 8; ++i2) {
                            int j2 = i2 + i1 * 8 << 11 | 0 + j1 * 8 << 7 | k1 * 4 + l1;
                            short short1 = 128;
                            double d14 = 0.125D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int k2 = 0; k2 < 8; ++k2) {
                                Block block = null;

                                if (d15 > 0.0D) {
                                    block = Blocks.WHITESTONE;
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

    public void b(int i, int j, Block[] ablock, BiomeBase[] abiomebase) {
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                byte b0 = 1;
                int i1 = -1;
                Block block = Blocks.WHITESTONE;
                Block block1 = Blocks.WHITESTONE;

                for (int j1 = 127; j1 >= 0; --j1) {
                    int k1 = (l * 16 + k) * 128 + j1;
                    Block block2 = ablock[k1];

                    if (block2 != null && block2.getMaterial() != Material.AIR) {
                        if (block2 == Blocks.STONE) {
                            if (i1 == -1) {
                                if (b0 <= 0) {
                                    block = null;
                                    block1 = Blocks.WHITESTONE;
                                }

                                i1 = b0;
                                if (j1 >= 0) {
                                    ablock[k1] = block;
                                } else {
                                    ablock[k1] = block1;
                                }
                            } else if (i1 > 0) {
                                --i1;
                                ablock[k1] = block1;
                            }
                        }
                    } else {
                        i1 = -1;
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

        this.o = this.m.getWorldChunkManager().getBiomeBlock(this.o, i * 16, j * 16, 16, 16);
        this.a(i, j, ablock, this.o);
        this.b(i, j, ablock, this.o);
        Chunk chunk = new Chunk(this.m, ablock, i, j);
        byte[] abyte = chunk.m();

        for (int k = 0; k < abyte.length; ++k) {
            abyte[k] = (byte) this.o[k].id;
        }

        chunk.initLighting();
        return chunk;
    }

    private double[] a(double[] adouble, int i, int j, int k, int l, int i1, int j1) {
        if (adouble == null) {
            adouble = new double[l * i1 * j1];
        }

        double d0 = 684.412D;
        double d1 = 684.412D;

        this.f = this.a.a(this.f, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        this.g = this.b.a(this.g, i, k, l, j1, 200.0D, 200.0D, 0.5D);
        d0 *= 2.0D;
        this.c = this.l.a(this.c, i, j, k, l, i1, j1, d0 / 80.0D, d1 / 160.0D, d0 / 80.0D);
        this.d = this.j.a(this.d, i, j, k, l, i1, j1, d0, d1, d0);
        this.e = this.k.a(this.e, i, j, k, l, i1, j1, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;

        for (int i2 = 0; i2 < l; ++i2) {
            for (int j2 = 0; j2 < j1; ++j2) {
                double d2 = (this.f[l1] + 256.0D) / 512.0D;

                if (d2 > 1.0D) {
                    d2 = 1.0D;
                }

                double d3 = this.g[l1] / 8000.0D;

                if (d3 < 0.0D) {
                    d3 = -d3 * 0.3D;
                }

                d3 = d3 * 3.0D - 2.0D;
                float f = (float) (i2 + i - 0) / 1.0F;
                float f1 = (float) (j2 + k - 0) / 1.0F;
                float f2 = 100.0F - MathHelper.c(f * f + f1 * f1) * 8.0F;

                if (f2 > 80.0F) {
                    f2 = 80.0F;
                }

                if (f2 < -100.0F) {
                    f2 = -100.0F;
                }

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

                    double d7 = this.d[k1] / 512.0D;
                    double d8 = this.e[k1] / 512.0D;
                    double d9 = (this.c[k1] / 10.0D + 1.0D) / 2.0D;

                    if (d9 < 0.0D) {
                        d5 = d7;
                    } else if (d9 > 1.0D) {
                        d5 = d8;
                    } else {
                        d5 = d7 + (d8 - d7) * d9;
                    }

                    d5 -= 8.0D;
                    d5 += (double) f2;
                    byte b0 = 2;
                    double d10;

                    if (k2 > i1 / 2 - b0) {
                        d10 = (double) ((float) (k2 - (i1 / 2 - b0)) / 64.0F);
                        if (d10 < 0.0D) {
                            d10 = 0.0D;
                        }

                        if (d10 > 1.0D) {
                            d10 = 1.0D;
                        }

                        d5 = d5 * (1.0D - d10) + -3000.0D * d10;
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
        BlockFalling.instaFall = true;
        int k = i * 16;
        int l = j * 16;
        BiomeBase biomebase = this.m.getBiome(k + 16, l + 16);

        biomebase.a(this.m, this.m.random, k, l);
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
        BiomeBase biomebase = this.m.getBiome(i, k);

        return biomebase.getMobs(enumcreaturetype);
    }

    public ChunkPosition findNearestMapFeature(World world, String s, int i, int j, int k) {
        return null;
    }

    public int getLoadedChunks() {
        return 0;
    }

    public void recreateStructures(int i, int j) {}
}

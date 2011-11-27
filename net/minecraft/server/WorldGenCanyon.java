package net.minecraft.server;

import java.util.Random;

public class WorldGenCanyon extends WorldGenBase {

    private float[] a = new float[1024];

    public WorldGenCanyon() {}

    protected void a(long i, int j, int k, byte[] abyte, double d0, double d1, double d2, float f, float f1, float f2, int l, int i1, double d3) {
        Random random = new Random(i);
        double d4 = (double) (j * 16 + 8);
        double d5 = (double) (k * 16 + 8);
        float f3 = 0.0F;
        float f4 = 0.0F;

        if (i1 <= 0) {
            int j1 = this.b * 16 - 16;

            i1 = j1 - random.nextInt(j1 / 4);
        }

        boolean flag = false;

        if (l == -1) {
            l = i1 / 2;
            flag = true;
        }

        float f5 = 1.0F;

        for (int k1 = 0; k1 < this.d.height; ++k1) {
            if (k1 == 0 || random.nextInt(3) == 0) {
                f5 = 1.0F + random.nextFloat() * random.nextFloat() * 1.0F;
            }

            this.a[k1] = f5 * f5;
        }

        for (; l < i1; ++l) {
            double d6 = 1.5D + (double) (MathHelper.sin((float) l * 3.1415927F / (float) i1) * f * 1.0F);
            double d7 = d6 * d3;

            d6 *= (double) random.nextFloat() * 0.25D + 0.75D;
            d7 *= (double) random.nextFloat() * 0.25D + 0.75D;
            float f6 = MathHelper.cos(f2);
            float f7 = MathHelper.sin(f2);

            d0 += (double) (MathHelper.cos(f1) * f6);
            d1 += (double) f7;
            d2 += (double) (MathHelper.sin(f1) * f6);
            f2 *= 0.7F;
            f2 += f4 * 0.05F;
            f1 += f3 * 0.05F;
            f4 *= 0.8F;
            f3 *= 0.5F;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
            if (flag || random.nextInt(4) != 0) {
                double d8 = d0 - d4;
                double d9 = d2 - d5;
                double d10 = (double) (i1 - l);
                double d11 = (double) (f + 2.0F + 16.0F);

                if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11) {
                    return;
                }

                if (d0 >= d4 - 16.0D - d6 * 2.0D && d2 >= d5 - 16.0D - d6 * 2.0D && d0 <= d4 + 16.0D + d6 * 2.0D && d2 <= d5 + 16.0D + d6 * 2.0D) {
                    int l1 = MathHelper.floor(d0 - d6) - j * 16 - 1;
                    int i2 = MathHelper.floor(d0 + d6) - j * 16 + 1;
                    int j2 = MathHelper.floor(d1 - d7) - 1;
                    int k2 = MathHelper.floor(d1 + d7) + 1;
                    int l2 = MathHelper.floor(d2 - d6) - k * 16 - 1;
                    int i3 = MathHelper.floor(d2 + d6) - k * 16 + 1;

                    if (l1 < 0) {
                        l1 = 0;
                    }

                    if (i2 > 16) {
                        i2 = 16;
                    }

                    if (j2 < 1) {
                        j2 = 1;
                    }

                    if (k2 > this.d.height - 8) {
                        k2 = this.d.height - 8;
                    }

                    if (l2 < 0) {
                        l2 = 0;
                    }

                    if (i3 > 16) {
                        i3 = 16;
                    }

                    boolean flag1 = false;

                    int j3;
                    int k3;

                    for (j3 = l1; !flag1 && j3 < i2; ++j3) {
                        for (int l3 = l2; !flag1 && l3 < i3; ++l3) {
                            for (int i4 = k2 + 1; !flag1 && i4 >= j2 - 1; --i4) {
                                k3 = (j3 * 16 + l3) * this.d.height + i4;
                                if (i4 >= 0 && i4 < this.d.height) {
                                    if (abyte[k3] == Block.WATER.id || abyte[k3] == Block.STATIONARY_WATER.id) {
                                        flag1 = true;
                                    }

                                    if (i4 != j2 - 1 && j3 != l1 && j3 != i2 - 1 && l3 != l2 && l3 != i3 - 1) {
                                        i4 = j2;
                                    }
                                }
                            }
                        }
                    }

                    if (!flag1) {
                        for (j3 = l1; j3 < i2; ++j3) {
                            double d12 = ((double) (j3 + j * 16) + 0.5D - d0) / d6;

                            for (k3 = l2; k3 < i3; ++k3) {
                                double d13 = ((double) (k3 + k * 16) + 0.5D - d2) / d6;
                                int j4 = (j3 * 16 + k3) * this.d.height + k2;
                                boolean flag2 = false;

                                if (d12 * d12 + d13 * d13 < 1.0D) {
                                    for (int k4 = k2 - 1; k4 >= j2; --k4) {
                                        double d14 = ((double) k4 + 0.5D - d1) / d7;

                                        if ((d12 * d12 + d13 * d13) * (double) this.a[k4] + d14 * d14 / 6.0D < 1.0D) {
                                            byte b0 = abyte[j4];

                                            if (b0 == Block.GRASS.id) {
                                                flag2 = true;
                                            }

                                            if (b0 == Block.STONE.id || b0 == Block.DIRT.id || b0 == Block.GRASS.id) {
                                                if (k4 < 10) {
                                                    abyte[j4] = (byte) Block.LAVA.id;
                                                } else {
                                                    abyte[j4] = 0;
                                                    if (flag2 && abyte[j4 - 1] == Block.DIRT.id) {
                                                        abyte[j4 - 1] = this.d.getWorldChunkManager().getBiome(j3 + j * 16, k3 + k * 16).t;
                                                    }
                                                }
                                            }
                                        }

                                        --j4;
                                    }
                                }
                            }
                        }

                        if (flag) {
                            break;
                        }
                    }
                }
            }
        }
    }

    protected void a(World world, int i, int j, int k, int l, byte[] abyte) {
        if (this.c.nextInt(50) == 0) {
            double d0 = (double) (i * 16 + this.c.nextInt(16));
            double d1 = (double) (this.c.nextInt(this.c.nextInt(40) + 8) + 20);
            double d2 = (double) (j * 16 + this.c.nextInt(16));
            byte b0 = 1;

            for (int i1 = 0; i1 < b0; ++i1) {
                float f = this.c.nextFloat() * 3.1415927F * 2.0F;
                float f1 = (this.c.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = (this.c.nextFloat() * 2.0F + this.c.nextFloat()) * 2.0F;

                this.a(this.c.nextLong(), k, l, abyte, d0, d1, d2, f2, f, f1, 0, 0, 3.0D);
            }
        }
    }
}

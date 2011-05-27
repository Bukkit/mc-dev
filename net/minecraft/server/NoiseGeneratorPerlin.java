package net.minecraft.server;

import java.util.Random;

public class NoiseGeneratorPerlin extends NoiseGenerator {

    private int[] d;
    public double a;
    public double b;
    public double c;

    public NoiseGeneratorPerlin() {
        this(new Random());
    }

    public NoiseGeneratorPerlin(Random random) {
        this.d = new int[512];
        this.a = random.nextDouble() * 256.0D;
        this.b = random.nextDouble() * 256.0D;
        this.c = random.nextDouble() * 256.0D;

        int i;

        for (i = 0; i < 256; this.d[i] = i++) {
            ;
        }

        for (i = 0; i < 256; ++i) {
            int j = random.nextInt(256 - i) + i;
            int k = this.d[i];

            this.d[i] = this.d[j];
            this.d[j] = k;
            this.d[i + 256] = this.d[i];
        }
    }

    public double a(double d0, double d1, double d2) {
        double d3 = d0 + this.a;
        double d4 = d1 + this.b;
        double d5 = d2 + this.c;
        int i = (int) d3;
        int j = (int) d4;
        int k = (int) d5;

        if (d3 < (double) i) {
            --i;
        }

        if (d4 < (double) j) {
            --j;
        }

        if (d5 < (double) k) {
            --k;
        }

        int l = i & 255;
        int i1 = j & 255;
        int j1 = k & 255;

        d3 -= (double) i;
        d4 -= (double) j;
        d5 -= (double) k;
        double d6 = d3 * d3 * d3 * (d3 * (d3 * 6.0D - 15.0D) + 10.0D);
        double d7 = d4 * d4 * d4 * (d4 * (d4 * 6.0D - 15.0D) + 10.0D);
        double d8 = d5 * d5 * d5 * (d5 * (d5 * 6.0D - 15.0D) + 10.0D);
        int k1 = this.d[l] + i1;
        int l1 = this.d[k1] + j1;
        int i2 = this.d[k1 + 1] + j1;
        int j2 = this.d[l + 1] + i1;
        int k2 = this.d[j2] + j1;
        int l2 = this.d[j2 + 1] + j1;

        return this.b(d8, this.b(d7, this.b(d6, this.a(this.d[l1], d3, d4, d5), this.a(this.d[k2], d3 - 1.0D, d4, d5)), this.b(d6, this.a(this.d[i2], d3, d4 - 1.0D, d5), this.a(this.d[l2], d3 - 1.0D, d4 - 1.0D, d5))), this.b(d7, this.b(d6, this.a(this.d[l1 + 1], d3, d4, d5 - 1.0D), this.a(this.d[k2 + 1], d3 - 1.0D, d4, d5 - 1.0D)), this.b(d6, this.a(this.d[i2 + 1], d3, d4 - 1.0D, d5 - 1.0D), this.a(this.d[l2 + 1], d3 - 1.0D, d4 - 1.0D, d5 - 1.0D))));
    }

    public final double b(double d0, double d1, double d2) {
        return d1 + d0 * (d2 - d1);
    }

    public final double a(int i, double d0, double d1) {
        int j = i & 15;
        double d2 = (double) (1 - ((j & 8) >> 3)) * d0;
        double d3 = j < 4 ? 0.0D : (j != 12 && j != 14 ? d1 : d0);

        return ((j & 1) == 0 ? d2 : -d2) + ((j & 2) == 0 ? d3 : -d3);
    }

    public final double a(int i, double d0, double d1, double d2) {
        int j = i & 15;
        double d3 = j < 8 ? d0 : d1;
        double d4 = j < 4 ? d1 : (j != 12 && j != 14 ? d2 : d0);

        return ((j & 1) == 0 ? d3 : -d3) + ((j & 2) == 0 ? d4 : -d4);
    }

    public double a(double d0, double d1) {
        return this.a(d0, d1, 0.0D);
    }

    public void a(double[] adouble, double d0, double d1, double d2, int i, int j, int k, double d3, double d4, double d5, double d6) {
        int l;
        int i1;
        double d7;
        double d8;
        double d9;
        int j1;
        double d10;
        int k1;
        int l1;
        int i2;
        int j2;

        if (j == 1) {
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            double d11 = 0.0D;
            double d12 = 0.0D;

            j2 = 0;
            double d13 = 1.0D / d6;

            for (int k2 = 0; k2 < i; ++k2) {
                d7 = (d0 + (double) k2) * d3 + this.a;
                int l2 = (int) d7;

                if (d7 < (double) l2) {
                    --l2;
                }

                int i3 = l2 & 255;

                d7 -= (double) l2;
                d8 = d7 * d7 * d7 * (d7 * (d7 * 6.0D - 15.0D) + 10.0D);

                for (j1 = 0; j1 < k; ++j1) {
                    d9 = (d2 + (double) j1) * d5 + this.c;
                    k1 = (int) d9;
                    if (d9 < (double) k1) {
                        --k1;
                    }

                    l1 = k1 & 255;
                    d9 -= (double) k1;
                    d10 = d9 * d9 * d9 * (d9 * (d9 * 6.0D - 15.0D) + 10.0D);
                    l = this.d[i3] + 0;
                    int j3 = this.d[l] + l1;
                    int k3 = this.d[i3 + 1] + 0;

                    i1 = this.d[k3] + l1;
                    d11 = this.b(d8, this.a(this.d[j3], d7, d9), this.a(this.d[i1], d7 - 1.0D, 0.0D, d9));
                    d12 = this.b(d8, this.a(this.d[j3 + 1], d7, 0.0D, d9 - 1.0D), this.a(this.d[i1 + 1], d7 - 1.0D, 0.0D, d9 - 1.0D));
                    double d14 = this.b(d10, d11, d12);

                    i2 = j2++;
                    adouble[i2] += d14 * d13;
                }
            }
        } else {
            l = 0;
            double d15 = 1.0D / d6;

            i1 = -1;
            boolean flag4 = false;
            boolean flag5 = false;
            boolean flag6 = false;
            boolean flag7 = false;
            boolean flag8 = false;
            boolean flag9 = false;
            double d16 = 0.0D;

            d7 = 0.0D;
            double d17 = 0.0D;

            d8 = 0.0D;

            for (j1 = 0; j1 < i; ++j1) {
                d9 = (d0 + (double) j1) * d3 + this.a;
                k1 = (int) d9;
                if (d9 < (double) k1) {
                    --k1;
                }

                l1 = k1 & 255;
                d9 -= (double) k1;
                d10 = d9 * d9 * d9 * (d9 * (d9 * 6.0D - 15.0D) + 10.0D);

                for (int l3 = 0; l3 < k; ++l3) {
                    double d18 = (d2 + (double) l3) * d5 + this.c;
                    int i4 = (int) d18;

                    if (d18 < (double) i4) {
                        --i4;
                    }

                    int j4 = i4 & 255;

                    d18 -= (double) i4;
                    double d19 = d18 * d18 * d18 * (d18 * (d18 * 6.0D - 15.0D) + 10.0D);

                    for (int k4 = 0; k4 < j; ++k4) {
                        double d20 = (d1 + (double) k4) * d4 + this.b;
                        int l4 = (int) d20;

                        if (d20 < (double) l4) {
                            --l4;
                        }

                        int i5 = l4 & 255;

                        d20 -= (double) l4;
                        double d21 = d20 * d20 * d20 * (d20 * (d20 * 6.0D - 15.0D) + 10.0D);

                        if (k4 == 0 || i5 != i1) {
                            i1 = i5;
                            int j5 = this.d[l1] + i5;
                            int k5 = this.d[j5] + j4;
                            int l5 = this.d[j5 + 1] + j4;
                            int i6 = this.d[l1 + 1] + i5;

                            j2 = this.d[i6] + j4;
                            int j6 = this.d[i6 + 1] + j4;

                            d16 = this.b(d10, this.a(this.d[k5], d9, d20, d18), this.a(this.d[j2], d9 - 1.0D, d20, d18));
                            d7 = this.b(d10, this.a(this.d[l5], d9, d20 - 1.0D, d18), this.a(this.d[j6], d9 - 1.0D, d20 - 1.0D, d18));
                            d17 = this.b(d10, this.a(this.d[k5 + 1], d9, d20, d18 - 1.0D), this.a(this.d[j2 + 1], d9 - 1.0D, d20, d18 - 1.0D));
                            d8 = this.b(d10, this.a(this.d[l5 + 1], d9, d20 - 1.0D, d18 - 1.0D), this.a(this.d[j6 + 1], d9 - 1.0D, d20 - 1.0D, d18 - 1.0D));
                        }

                        double d22 = this.b(d21, d16, d7);
                        double d23 = this.b(d21, d17, d8);
                        double d24 = this.b(d19, d22, d23);

                        i2 = l++;
                        adouble[i2] += d24 * d15;
                    }
                }
            }
        }
    }
}

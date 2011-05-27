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
        boolean flag;
        int i1;
        boolean flag1;
        double d7;
        double d8;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;

        if (j == 1) {
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag4 = false;
            boolean flag5 = false;

            flag = false;
            flag1 = false;
            double d9 = 0.0D;
            double d10 = 0.0D;

            d7 = 0.0D;
            d8 = 0.0D;
            int k2 = 0;
            double d11 = 1.0D / d6;

            for (int l2 = 0; l2 < i; ++l2) {
                double d12 = (d0 + (double) l2) * d3 + this.a;
                int i3 = (int) d12;

                if (d12 < (double) i3) {
                    --i3;
                }

                j1 = i3 & 255;
                d12 -= (double) i3;
                double d13 = d12 * d12 * d12 * (d12 * (d12 * 6.0D - 15.0D) + 10.0D);

                for (int j3 = 0; j3 < k; ++j3) {
                    double d14 = (d2 + (double) j3) * d5 + this.c;
                    int k3 = (int) d14;

                    if (d14 < (double) k3) {
                        --k3;
                    }

                    l1 = k3 & 255;
                    d14 -= (double) k3;
                    double d15 = d14 * d14 * d14 * (d14 * (d14 * 6.0D - 15.0D) + 10.0D);

                    l = this.d[j1] + 0;
                    int l3 = this.d[l] + l1;
                    int i4 = this.d[l + 1] + l1;

                    i1 = this.d[j1 + 1] + 0;
                    j2 = this.d[i1] + l1;
                    i2 = this.d[i1 + 1] + l1;
                    d9 = this.b(d13, this.a(this.d[l3], d12, d14), this.a(this.d[j2], d12 - 1.0D, 0.0D, d14));
                    d7 = this.b(d13, this.a(this.d[l3 + 1], d12, 0.0D, d14 - 1.0D), this.a(this.d[j2 + 1], d12 - 1.0D, 0.0D, d14 - 1.0D));
                    double d16 = this.b(d15, d9, d7);

                    k1 = k2++;
                    adouble[k1] += d16 * d11;
                }
            }
        } else {
            l = 0;
            double d17 = 1.0D / d6;

            i1 = -1;
            flag = false;
            flag1 = false;
            boolean flag6 = false;
            boolean flag7 = false;
            boolean flag8 = false;
            boolean flag9 = false;

            d7 = 0.0D;
            d8 = 0.0D;
            double d18 = 0.0D;
            double d19 = 0.0D;

            for (int j4 = 0; j4 < i; ++j4) {
                double d20 = (d0 + (double) j4) * d3 + this.a;

                j1 = (int) d20;
                if (d20 < (double) j1) {
                    --j1;
                }

                int k4 = j1 & 255;

                d20 -= (double) j1;
                double d21 = d20 * d20 * d20 * (d20 * (d20 * 6.0D - 15.0D) + 10.0D);

                for (int l4 = 0; l4 < k; ++l4) {
                    double d22 = (d2 + (double) l4) * d5 + this.c;

                    l1 = (int) d22;
                    if (d22 < (double) l1) {
                        --l1;
                    }

                    int i5 = l1 & 255;

                    d22 -= (double) l1;
                    double d23 = d22 * d22 * d22 * (d22 * (d22 * 6.0D - 15.0D) + 10.0D);

                    for (int j5 = 0; j5 < j; ++j5) {
                        double d24 = (d1 + (double) j5) * d4 + this.b;
                        int k5 = (int) d24;

                        if (d24 < (double) k5) {
                            --k5;
                        }

                        int l5 = k5 & 255;

                        d24 -= (double) k5;
                        double d25 = d24 * d24 * d24 * (d24 * (d24 * 6.0D - 15.0D) + 10.0D);

                        if (j5 == 0 || l5 != i1) {
                            i1 = l5;
                            j2 = this.d[k4] + l5;
                            i2 = this.d[j2] + i5;
                            int i6 = this.d[j2 + 1] + i5;
                            int j6 = this.d[k4 + 1] + l5;
                            int k6 = this.d[j6] + i5;
                            int l6 = this.d[j6 + 1] + i5;

                            d7 = this.b(d21, this.a(this.d[i2], d20, d24, d22), this.a(this.d[k6], d20 - 1.0D, d24, d22));
                            d8 = this.b(d21, this.a(this.d[i6], d20, d24 - 1.0D, d22), this.a(this.d[l6], d20 - 1.0D, d24 - 1.0D, d22));
                            d18 = this.b(d21, this.a(this.d[i2 + 1], d20, d24, d22 - 1.0D), this.a(this.d[k6 + 1], d20 - 1.0D, d24, d22 - 1.0D));
                            d19 = this.b(d21, this.a(this.d[i6 + 1], d20, d24 - 1.0D, d22 - 1.0D), this.a(this.d[l6 + 1], d20 - 1.0D, d24 - 1.0D, d22 - 1.0D));
                        }

                        double d26 = this.b(d25, d7, d8);
                        double d27 = this.b(d25, d18, d19);
                        double d28 = this.b(d23, d26, d27);

                        k1 = l++;
                        adouble[k1] += d28 * d17;
                    }
                }
            }
        }
    }
}

package net.minecraft.server;

import java.util.Random;

public class NoiseGenerator2 {

    private static int[][] d = new int[][] { { 1, 1, 0}, { -1, 1, 0}, { 1, -1, 0}, { -1, -1, 0}, { 1, 0, 1}, { -1, 0, 1}, { 1, 0, -1}, { -1, 0, -1}, { 0, 1, 1}, { 0, -1, 1}, { 0, 1, -1}, { 0, -1, -1}};
    private int[] e;
    public double a;
    public double b;
    public double c;
    private static final double f = 0.5D * (Math.sqrt(3.0D) - 1.0D);
    private static final double g = (3.0D - Math.sqrt(3.0D)) / 6.0D;

    public NoiseGenerator2() {
        this(new Random());
    }

    public NoiseGenerator2(Random random) {
        this.e = new int[512];
        this.a = random.nextDouble() * 256.0D;
        this.b = random.nextDouble() * 256.0D;
        this.c = random.nextDouble() * 256.0D;

        int i;

        for (i = 0; i < 256; this.e[i] = i++) {
            ;
        }

        for (i = 0; i < 256; ++i) {
            int j = random.nextInt(256 - i) + i;
            int k = this.e[i];

            this.e[i] = this.e[j];
            this.e[j] = k;
            this.e[i + 256] = this.e[i];
        }
    }

    private static int a(double d0) {
        return d0 > 0.0D ? (int) d0 : (int) d0 - 1;
    }

    private static double a(int[] aint, double d0, double d1) {
        return (double) aint[0] * d0 + (double) aint[1] * d1;
    }

    public void a(double[] adouble, double d0, double d1, int i, int j, double d2, double d3, double d4) {
        int k = 0;

        for (int l = 0; l < i; ++l) {
            double d5 = (d0 + (double) l) * d2 + this.a;

            for (int i1 = 0; i1 < j; ++i1) {
                double d6 = (d1 + (double) i1) * d3 + this.b;
                double d7 = (d5 + d6) * f;
                int j1 = a(d5 + d7);
                int k1 = a(d6 + d7);
                double d8 = (double) (j1 + k1) * g;
                double d9 = (double) j1 - d8;
                double d10 = (double) k1 - d8;
                double d11 = d5 - d9;
                double d12 = d6 - d10;
                byte b0;
                byte b1;

                if (d11 > d12) {
                    b0 = 1;
                    b1 = 0;
                } else {
                    b0 = 0;
                    b1 = 1;
                }

                double d13 = d11 - (double) b0 + g;
                double d14 = d12 - (double) b1 + g;
                double d15 = d11 - 1.0D + 2.0D * g;
                double d16 = d12 - 1.0D + 2.0D * g;
                int l1 = j1 & 255;
                int i2 = k1 & 255;
                int j2 = this.e[l1 + this.e[i2]] % 12;
                int k2 = this.e[l1 + b0 + this.e[i2 + b1]] % 12;
                int l2 = this.e[l1 + 1 + this.e[i2 + 1]] % 12;
                double d17 = 0.5D - d11 * d11 - d12 * d12;
                double d18;

                if (d17 < 0.0D) {
                    d18 = 0.0D;
                } else {
                    d17 *= d17;
                    d18 = d17 * d17 * a(d[j2], d11, d12);
                }

                double d19 = 0.5D - d13 * d13 - d14 * d14;
                double d20;

                if (d19 < 0.0D) {
                    d20 = 0.0D;
                } else {
                    d19 *= d19;
                    d20 = d19 * d19 * a(d[k2], d13, d14);
                }

                double d21 = 0.5D - d15 * d15 - d16 * d16;
                double d22;

                if (d21 < 0.0D) {
                    d22 = 0.0D;
                } else {
                    d21 *= d21;
                    d22 = d21 * d21 * a(d[l2], d15, d16);
                }

                int i3 = k++;

                adouble[i3] += 70.0D * (d18 + d20 + d22) * d4;
            }
        }
    }
}

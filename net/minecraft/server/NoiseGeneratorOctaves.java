package net.minecraft.server;

import java.util.Random;

public class NoiseGeneratorOctaves extends NoiseGenerator {

    private NoiseGeneratorPerlin[] a;
    private int b;

    public NoiseGeneratorOctaves(Random random, int i) {
        this.b = i;
        this.a = new NoiseGeneratorPerlin[i];

        for (int j = 0; j < i; ++j) {
            this.a[j] = new NoiseGeneratorPerlin(random);
        }
    }

    public double a(double d0, double d1) {
        double d2 = 0.0D;
        double d3 = 1.0D;

        for (int i = 0; i < this.b; ++i) {
            d2 += this.a[i].a(d0 * d3, d1 * d3) / d3;
            d3 /= 2.0D;
        }

        return d2;
    }

    public double[] a(double[] adouble, double d0, double d1, double d2, int i, int j, int k, double d3, double d4, double d5) {
        if (adouble == null) {
            adouble = new double[i * j * k];
        } else {
            for (int l = 0; l < adouble.length; ++l) {
                adouble[l] = 0.0D;
            }
        }

        double d6 = 1.0D;

        for (int i1 = 0; i1 < this.b; ++i1) {
            this.a[i1].a(adouble, d0, d1, d2, i, j, k, d3 * d6, d4 * d6, d5 * d6, d6);
            d6 /= 2.0D;
        }

        return adouble;
    }

    public double[] a(double[] adouble, int i, int j, int k, int l, double d0, double d1, double d2) {
        return this.a(adouble, (double) i, 10.0D, (double) j, k, 1, l, d0, 1.0D, d1);
    }
}

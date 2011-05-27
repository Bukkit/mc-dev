package net.minecraft.server;

import java.util.Random;

public class NoiseGeneratorOctaves2 extends NoiseGenerator {

    private NoiseGenerator2[] a;
    private int b;

    public NoiseGeneratorOctaves2(Random random, int i) {
        this.b = i;
        this.a = new NoiseGenerator2[i];

        for (int j = 0; j < i; ++j) {
            this.a[j] = new NoiseGenerator2(random);
        }
    }

    public double[] a(double[] adouble, double d0, double d1, int i, int j, double d2, double d3, double d4) {
        return this.a(adouble, d0, d1, i, j, d2, d3, d4, 0.5D);
    }

    public double[] a(double[] adouble, double d0, double d1, int i, int j, double d2, double d3, double d4, double d5) {
        d2 /= 1.5D;
        d3 /= 1.5D;
        if (adouble != null && adouble.length >= i * j) {
            for (int k = 0; k < adouble.length; ++k) {
                adouble[k] = 0.0D;
            }
        } else {
            adouble = new double[i * j];
        }

        double d6 = 1.0D;
        double d7 = 1.0D;

        for (int l = 0; l < this.b; ++l) {
            this.a[l].a(adouble, d0, d1, i, j, d2 * d7, d3 * d7, 0.55D / d6);
            d7 *= d4;
            d6 *= d5;
        }

        return adouble;
    }
}

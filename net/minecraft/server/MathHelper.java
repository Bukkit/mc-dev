package net.minecraft.server;

import java.util.Random;

public class MathHelper {

    private static float[] a = new float[65536];

    public MathHelper() {}

    public static final float sin(float f) {
        return a[(int) (f * 10430.378F) & '\uffff'];
    }

    public static final float cos(float f) {
        return a[(int) (f * 10430.378F + 16384.0F) & '\uffff'];
    }

    public static final float c(float f) {
        return (float) Math.sqrt((double) f);
    }

    public static final float a(double d0) {
        return (float) Math.sqrt(d0);
    }

    public static int d(float f) {
        int i = (int) f;

        return f < (float) i ? i - 1 : i;
    }

    public static int floor(double d0) {
        int i = (int) d0;

        return d0 < (double) i ? i - 1 : i;
    }

    public static long c(double d0) {
        long i = (long) d0;

        return d0 < (double) i ? i - 1L : i;
    }

    public static float abs(float f) {
        return f >= 0.0F ? f : -f;
    }

    public static int a(int i) {
        return i >= 0 ? i : -i;
    }

    public static int a(int i, int j, int k) {
        return i < j ? j : (i > k ? k : i);
    }

    public static double a(double d0, double d1) {
        if (d0 < 0.0D) {
            d0 = -d0;
        }

        if (d1 < 0.0D) {
            d1 = -d1;
        }

        return d0 > d1 ? d0 : d1;
    }

    public static int a(Random random, int i, int j) {
        return i >= j ? i : random.nextInt(j - i + 1) + i;
    }

    static {
        for (int i = 0; i < 65536; ++i) {
            a[i] = (float) Math.sin((double) i * 3.141592653589793D * 2.0D / 65536.0D);
        }
    }
}

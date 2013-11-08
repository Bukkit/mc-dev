package net.minecraft.server;

import java.util.Random;

class Location2D {

    double a;
    double b;

    Location2D() {}

    Location2D(double d0, double d1) {
        this.a = d0;
        this.b = d1;
    }

    double a(Location2D location2d) {
        double d0 = this.a - location2d.a;
        double d1 = this.b - location2d.b;

        return Math.sqrt(d0 * d0 + d1 * d1);
    }

    void a() {
        double d0 = (double) this.b();

        this.a /= d0;
        this.b /= d0;
    }

    float b() {
        return MathHelper.sqrt(this.a * this.a + this.b * this.b);
    }

    public void b(Location2D location2d) {
        this.a -= location2d.a;
        this.b -= location2d.b;
    }

    public boolean a(double d0, double d1, double d2, double d3) {
        boolean flag = false;

        if (this.a < d0) {
            this.a = d0;
            flag = true;
        } else if (this.a > d2) {
            this.a = d2;
            flag = true;
        }

        if (this.b < d1) {
            this.b = d1;
            flag = true;
        } else if (this.b > d3) {
            this.b = d3;
            flag = true;
        }

        return flag;
    }

    public int a(World world) {
        int i = MathHelper.floor(this.a);
        int j = MathHelper.floor(this.b);

        for (int k = 256; k > 0; --k) {
            if (world.getType(i, k, j).getMaterial() != Material.AIR) {
                return k + 1;
            }
        }

        return 257;
    }

    public boolean b(World world) {
        int i = MathHelper.floor(this.a);
        int j = MathHelper.floor(this.b);
        short short1 = 256;

        if (short1 <= 0) {
            return false;
        } else {
            Material material = world.getType(i, short1, j).getMaterial();

            return !material.isLiquid() && material != Material.FIRE;
        }
    }

    public void a(Random random, double d0, double d1, double d2, double d3) {
        this.a = MathHelper.a(random, d0, d2);
        this.b = MathHelper.a(random, d1, d3);
    }
}

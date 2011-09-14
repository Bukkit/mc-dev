package net.minecraft.server;

import java.util.Random;

public class BiomeSwamp extends BiomeBase {

    protected BiomeSwamp(int i) {
        super(i);
        this.u.r = 2;
        this.u.s = -999;
        this.u.u = 1;
        this.u.v = 8;
        this.u.w = 10;
        this.u.A = 1;
    }

    public WorldGenerator a(Random random) {
        return this.C;
    }
}

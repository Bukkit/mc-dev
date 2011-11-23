package net.minecraft.server;

import java.util.Random;

public class BiomeSwamp extends BiomeBase {

    protected BiomeSwamp(int i) {
        super(i);
        this.B.z = 2;
        this.B.A = -999;
        this.B.C = 1;
        this.B.D = 8;
        this.B.E = 10;
        this.B.I = 1;
        this.B.y = 4;
        this.A = 14745456;
    }

    public WorldGenerator a(Random random) {
        return this.J;
    }
}

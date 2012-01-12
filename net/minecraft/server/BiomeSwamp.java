package net.minecraft.server;

import java.util.Random;

public class BiomeSwamp extends BiomeBase {

    protected BiomeSwamp(int i) {
        super(i);
        this.G.z = 2;
        this.G.A = -999;
        this.G.C = 1;
        this.G.D = 8;
        this.G.E = 10;
        this.G.I = 1;
        this.G.y = 4;
        this.F = 14745518;
    }

    public WorldGenerator a(Random random) {
        return this.O;
    }
}

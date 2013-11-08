package net.minecraft.server;

import java.util.Random;

public class BiomeOcean extends BiomeBase {

    public BiomeOcean(int i) {
        super(i);
        this.at.clear();
    }

    public EnumTemperature m() {
        return EnumTemperature.OCEAN;
    }

    public void a(World world, Random random, Block[] ablock, byte[] abyte, int i, int j, double d0) {
        super.a(world, random, ablock, abyte, i, j, d0);
    }
}

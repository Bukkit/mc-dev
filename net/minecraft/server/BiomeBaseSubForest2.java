package net.minecraft.server;

import java.util.Random;

class BiomeBaseSubForest2 extends BiomeBaseSub {

    final BiomeForest aC;

    BiomeBaseSubForest2(BiomeForest biomeforest, int i, BiomeBase biomebase) {
        super(i, biomebase);
        this.aC = biomeforest;
    }

    public void a(World world, Random random, int i, int j) {
        this.aD.a(world, random, i, j);
    }
}

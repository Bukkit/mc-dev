package net.minecraft.server;

import java.util.Random;

class BiomeBaseSubForest extends BiomeBaseSub {

    final BiomeForest aC;

    BiomeBaseSubForest(BiomeForest biomeforest, int i, BiomeBase biomebase) {
        super(i, biomebase);
        this.aC = biomeforest;
    }

    public WorldGenTreeAbstract a(Random random) {
        return random.nextBoolean() ? BiomeForest.aC : BiomeForest.aD;
    }
}

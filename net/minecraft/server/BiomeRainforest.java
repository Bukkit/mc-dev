package net.minecraft.server;

import java.util.Random;

public class BiomeRainforest extends BiomeBase {

    public BiomeRainforest() {}

    public WorldGenerator a(Random random) {
        return (WorldGenerator) (random.nextInt(3) == 0 ? new WorldGenBigTree() : new WorldGenTrees());
    }
}

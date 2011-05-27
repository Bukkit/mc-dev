package net.minecraft.server;

import java.util.Random;

public class BiomeTaiga extends BiomeBase {

    public BiomeTaiga() {}

    public WorldGenerator a(Random random) {
        return (WorldGenerator) (random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2());
    }
}

package net.minecraft.server;

import java.util.Random;

public class WorldGenLargeFeatureStart extends StructureStart {

    public WorldGenLargeFeatureStart(World world, Random random, int i, int j) {
        if (world.getBiome(i * 16 + 8, j * 16 + 8) == BiomeBase.JUNGLE) {
            WorldGenJungleTemple worldgenjungletemple = new WorldGenJungleTemple(random, i * 16, j * 16);

            this.a.add(worldgenjungletemple);
        } else {
            WorldGenPyramidPiece worldgenpyramidpiece = new WorldGenPyramidPiece(random, i * 16, j * 16);

            this.a.add(worldgenpyramidpiece);
        }

        this.c();
    }
}

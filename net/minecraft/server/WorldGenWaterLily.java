package net.minecraft.server;

import java.util.Random;

public class WorldGenWaterLily extends WorldGenerator {

    public WorldGenWaterLily() {}

    public boolean a(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < 10; ++l) {
            int i1 = i + random.nextInt(8) - random.nextInt(8);
            int j1 = j + random.nextInt(4) - random.nextInt(4);
            int k1 = k + random.nextInt(8) - random.nextInt(8);

            if (world.isEmpty(i1, j1, k1) && Blocks.WATER_LILY.canPlace(world, i1, j1, k1)) {
                world.setTypeAndData(i1, j1, k1, Blocks.WATER_LILY, 0, 2);
            }
        }

        return true;
    }
}

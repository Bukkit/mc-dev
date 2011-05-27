package net.minecraft.server;

import java.util.Random;

public class WorldGenPumpkin extends WorldGenerator {

    public WorldGenPumpkin() {}

    public boolean a(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < 64; ++l) {
            int i1 = i + random.nextInt(8) - random.nextInt(8);
            int j1 = j + random.nextInt(4) - random.nextInt(4);
            int k1 = k + random.nextInt(8) - random.nextInt(8);

            if (world.a(i1, j1, k1) == 0 && world.a(i1, j1 - 1, k1) == Block.GRASS.bi && Block.PUMPKIN.a(world, i1, j1, k1)) {
                world.a(i1, j1, k1, Block.PUMPKIN.bi, random.nextInt(4));
            }
        }

        return true;
    }
}

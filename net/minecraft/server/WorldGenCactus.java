package net.minecraft.server;

import java.util.Random;

public class WorldGenCactus extends WorldGenerator {

    public WorldGenCactus() {}

    public boolean a(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < 10; ++l) {
            int i1 = i + random.nextInt(8) - random.nextInt(8);
            int j1 = j + random.nextInt(4) - random.nextInt(4);
            int k1 = k + random.nextInt(8) - random.nextInt(8);

            if (world.isEmpty(i1, j1, k1)) {
                int l1 = 1 + random.nextInt(random.nextInt(3) + 1);

                for (int i2 = 0; i2 < l1; ++i2) {
                    if (Block.CACTUS.f(world, i1, j1 + i2, k1)) {
                        world.setRawTypeId(i1, j1 + i2, k1, Block.CACTUS.id);
                    }
                }
            }
        }

        return true;
    }
}

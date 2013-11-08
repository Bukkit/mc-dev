package net.minecraft.server;

import java.util.Random;

public class WorldGenReed extends WorldGenerator {

    public WorldGenReed() {}

    public boolean a(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < 20; ++l) {
            int i1 = i + random.nextInt(4) - random.nextInt(4);
            int j1 = j;
            int k1 = k + random.nextInt(4) - random.nextInt(4);

            if (world.isEmpty(i1, j, k1) && (world.getType(i1 - 1, j - 1, k1).getMaterial() == Material.WATER || world.getType(i1 + 1, j - 1, k1).getMaterial() == Material.WATER || world.getType(i1, j - 1, k1 - 1).getMaterial() == Material.WATER || world.getType(i1, j - 1, k1 + 1).getMaterial() == Material.WATER)) {
                int l1 = 2 + random.nextInt(random.nextInt(3) + 1);

                for (int i2 = 0; i2 < l1; ++i2) {
                    if (Blocks.SUGAR_CANE_BLOCK.j(world, i1, j1 + i2, k1)) {
                        world.setTypeAndData(i1, j1 + i2, k1, Blocks.SUGAR_CANE_BLOCK, 0, 2);
                    }
                }
            }
        }

        return true;
    }
}

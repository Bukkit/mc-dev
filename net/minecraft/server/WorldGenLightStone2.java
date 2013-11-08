package net.minecraft.server;

import java.util.Random;

public class WorldGenLightStone2 extends WorldGenerator {

    public WorldGenLightStone2() {}

    public boolean a(World world, Random random, int i, int j, int k) {
        if (!world.isEmpty(i, j, k)) {
            return false;
        } else if (world.getType(i, j + 1, k) != Blocks.NETHERRACK) {
            return false;
        } else {
            world.setTypeAndData(i, j, k, Blocks.GLOWSTONE, 0, 2);

            for (int l = 0; l < 1500; ++l) {
                int i1 = i + random.nextInt(8) - random.nextInt(8);
                int j1 = j - random.nextInt(12);
                int k1 = k + random.nextInt(8) - random.nextInt(8);

                if (world.getType(i1, j1, k1).getMaterial() == Material.AIR) {
                    int l1 = 0;

                    for (int i2 = 0; i2 < 6; ++i2) {
                        Block block = null;

                        if (i2 == 0) {
                            block = world.getType(i1 - 1, j1, k1);
                        }

                        if (i2 == 1) {
                            block = world.getType(i1 + 1, j1, k1);
                        }

                        if (i2 == 2) {
                            block = world.getType(i1, j1 - 1, k1);
                        }

                        if (i2 == 3) {
                            block = world.getType(i1, j1 + 1, k1);
                        }

                        if (i2 == 4) {
                            block = world.getType(i1, j1, k1 - 1);
                        }

                        if (i2 == 5) {
                            block = world.getType(i1, j1, k1 + 1);
                        }

                        if (block == Blocks.GLOWSTONE) {
                            ++l1;
                        }
                    }

                    if (l1 == 1) {
                        world.setTypeAndData(i1, j1, k1, Blocks.GLOWSTONE, 0, 2);
                    }
                }
            }

            return true;
        }
    }
}

package net.minecraft.server;

import java.util.Random;

public class WorldGenDesertWell extends WorldGenerator {

    public WorldGenDesertWell() {}

    public boolean a(World world, Random random, int i, int j, int k) {
        while (world.isEmpty(i, j, k) && j > 2) {
            --j;
        }

        if (world.getType(i, j, k) != Blocks.SAND) {
            return false;
        } else {
            int l;
            int i1;

            for (l = -2; l <= 2; ++l) {
                for (i1 = -2; i1 <= 2; ++i1) {
                    if (world.isEmpty(i + l, j - 1, k + i1) && world.isEmpty(i + l, j - 2, k + i1)) {
                        return false;
                    }
                }
            }

            for (l = -1; l <= 0; ++l) {
                for (i1 = -2; i1 <= 2; ++i1) {
                    for (int j1 = -2; j1 <= 2; ++j1) {
                        world.setTypeAndData(i + i1, j + l, k + j1, Blocks.SANDSTONE, 0, 2);
                    }
                }
            }

            world.setTypeAndData(i, j, k, Blocks.WATER, 0, 2);
            world.setTypeAndData(i - 1, j, k, Blocks.WATER, 0, 2);
            world.setTypeAndData(i + 1, j, k, Blocks.WATER, 0, 2);
            world.setTypeAndData(i, j, k - 1, Blocks.WATER, 0, 2);
            world.setTypeAndData(i, j, k + 1, Blocks.WATER, 0, 2);

            for (l = -2; l <= 2; ++l) {
                for (i1 = -2; i1 <= 2; ++i1) {
                    if (l == -2 || l == 2 || i1 == -2 || i1 == 2) {
                        world.setTypeAndData(i + l, j + 1, k + i1, Blocks.SANDSTONE, 0, 2);
                    }
                }
            }

            world.setTypeAndData(i + 2, j + 1, k, Blocks.STEP, 1, 2);
            world.setTypeAndData(i - 2, j + 1, k, Blocks.STEP, 1, 2);
            world.setTypeAndData(i, j + 1, k + 2, Blocks.STEP, 1, 2);
            world.setTypeAndData(i, j + 1, k - 2, Blocks.STEP, 1, 2);

            for (l = -1; l <= 1; ++l) {
                for (i1 = -1; i1 <= 1; ++i1) {
                    if (l == 0 && i1 == 0) {
                        world.setTypeAndData(i + l, j + 4, k + i1, Blocks.SANDSTONE, 0, 2);
                    } else {
                        world.setTypeAndData(i + l, j + 4, k + i1, Blocks.STEP, 1, 2);
                    }
                }
            }

            for (l = 1; l <= 3; ++l) {
                world.setTypeAndData(i - 1, j + l, k - 1, Blocks.SANDSTONE, 0, 2);
                world.setTypeAndData(i - 1, j + l, k + 1, Blocks.SANDSTONE, 0, 2);
                world.setTypeAndData(i + 1, j + l, k - 1, Blocks.SANDSTONE, 0, 2);
                world.setTypeAndData(i + 1, j + l, k + 1, Blocks.SANDSTONE, 0, 2);
            }

            return true;
        }
    }
}

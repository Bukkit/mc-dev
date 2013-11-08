package net.minecraft.server;

import java.util.Random;

public class WorldGenVines extends WorldGenerator {

    public WorldGenVines() {}

    public boolean a(World world, Random random, int i, int j, int k) {
        int l = i;

        for (int i1 = k; j < 128; ++j) {
            if (world.isEmpty(i, j, k)) {
                for (int j1 = 2; j1 <= 5; ++j1) {
                    if (Blocks.VINE.canPlace(world, i, j, k, j1)) {
                        world.setTypeAndData(i, j, k, Blocks.VINE, 1 << Direction.e[Facing.OPPOSITE_FACING[j1]], 2);
                        break;
                    }
                }
            } else {
                i = l + random.nextInt(4) - random.nextInt(4);
                k = i1 + random.nextInt(4) - random.nextInt(4);
            }
        }

        return true;
    }
}

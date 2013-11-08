package net.minecraft.server;

import java.util.Random;

public class WorldGenMelon extends WorldGenerator {

    public WorldGenMelon() {}

    public boolean a(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < 64; ++l) {
            int i1 = i + random.nextInt(8) - random.nextInt(8);
            int j1 = j + random.nextInt(4) - random.nextInt(4);
            int k1 = k + random.nextInt(8) - random.nextInt(8);

            if (Blocks.MELON.canPlace(world, i1, j1, k1) && world.getType(i1, j1 - 1, k1) == Blocks.GRASS) {
                world.setTypeAndData(i1, j1, k1, Blocks.MELON, 0, 2);
            }
        }

        return true;
    }
}

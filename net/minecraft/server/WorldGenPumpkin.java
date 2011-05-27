package net.minecraft.server;

import java.util.Random;

public class WorldGenPumpkin extends WorldGenerator {

    public WorldGenPumpkin() {}

    public boolean a(World world, Random random, int i, int j, int k) {
        for (int l = 0; l < 64; ++l) {
            int i1 = i + random.nextInt(8) - random.nextInt(8);
            int j1 = j + random.nextInt(4) - random.nextInt(4);
            int k1 = k + random.nextInt(8) - random.nextInt(8);

            if (world.isEmpty(i1, j1, k1) && world.getTypeId(i1, j1 - 1, k1) == Block.GRASS.id && Block.PUMPKIN.canPlace(world, i1, j1, k1)) {
                world.setRawTypeIdAndData(i1, j1, k1, Block.PUMPKIN.id, random.nextInt(4));
            }
        }

        return true;
    }
}

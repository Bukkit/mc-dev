package net.minecraft.server;

import java.util.Random;

public class WorldGenTallPlant extends WorldGenerator {

    private int a;

    public WorldGenTallPlant() {}

    public void a(int i) {
        this.a = i;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        boolean flag = false;

        for (int l = 0; l < 64; ++l) {
            int i1 = i + random.nextInt(8) - random.nextInt(8);
            int j1 = j + random.nextInt(4) - random.nextInt(4);
            int k1 = k + random.nextInt(8) - random.nextInt(8);

            if (world.isEmpty(i1, j1, k1) && (!world.worldProvider.g || j1 < 254) && Blocks.DOUBLE_PLANT.canPlace(world, i1, j1, k1)) {
                Blocks.DOUBLE_PLANT.c(world, i1, j1, k1, this.a, 2);
                flag = true;
            }
        }

        return flag;
    }
}

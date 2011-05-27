package net.minecraft.server;

import java.util.Random;

public class BlockSapling extends BlockFlower {

    protected BlockSapling(int i, int j) {
        super(i, j);
        float f = 0.4F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    public void a(World world, int i, int j, int k, Random random) {
        super.a(world, i, j, k, random);
        if (world.h(i, j + 1, k) >= 9 && random.nextInt(5) == 0) {
            int l = world.b(i, j, k);

            if (l < 15) {
                world.b(i, j, k, l + 1);
            } else {
                world.a(i, j, k, 0);
                Object object = new WorldGenTrees();

                if (random.nextInt(10) == 0) {
                    object = new WorldGenBigTree();
                }

                if (!((WorldGenerator) object).a(world, random, i, j, k)) {
                    world.a(i, j, k, this.bi);
                }
            }
        }
    }
}

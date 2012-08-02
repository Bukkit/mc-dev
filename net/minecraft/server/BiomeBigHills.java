package net.minecraft.server;

import java.util.Random;

public class BiomeBigHills extends BiomeBase {

    protected BiomeBigHills(int i) {
        super(i);
    }

    public void a(World world, Random random, int i, int j) {
        super.a(world, random, i, j);
        int k = 3 + random.nextInt(6);

        for (int l = 0; l < k; ++l) {
            int i1 = i + random.nextInt(16);
            int j1 = random.nextInt(28) + 4;
            int k1 = j + random.nextInt(16);
            int l1 = world.getTypeId(i1, j1, k1);

            if (l1 == Block.STONE.id) {
                world.setRawTypeId(i1, j1, k1, Block.EMERALD_ORE.id);
            }
        }
    }
}

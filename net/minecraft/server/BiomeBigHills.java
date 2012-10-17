package net.minecraft.server;

import java.util.Random;

public class BiomeBigHills extends BiomeBase {

    private WorldGenerator S;

    protected BiomeBigHills(int i) {
        super(i);
        this.S = new WorldGenMinable(Block.MONSTER_EGGS.id, 8);
    }

    public void a(World world, Random random, int i, int j) {
        super.a(world, random, i, j);
        int k = 3 + random.nextInt(6);

        int l;
        int i1;
        int j1;

        for (l = 0; l < k; ++l) {
            i1 = i + random.nextInt(16);
            j1 = random.nextInt(28) + 4;
            int k1 = j + random.nextInt(16);
            int l1 = world.getTypeId(i1, j1, k1);

            if (l1 == Block.STONE.id) {
                world.setRawTypeId(i1, j1, k1, Block.EMERALD_ORE.id);
            }
        }

        for (k = 0; k < 7; ++k) {
            l = i + random.nextInt(16);
            i1 = random.nextInt(64);
            j1 = j + random.nextInt(16);
            this.S.a(world, random, l, i1, j1);
        }
    }
}

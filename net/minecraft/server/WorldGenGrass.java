package net.minecraft.server;

import java.util.Random;

public class WorldGenGrass extends WorldGenerator {

    private int a;
    private int b;

    public WorldGenGrass(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        int l;

        for (boolean flag = false; ((l = world.getTypeId(i, j, k)) == 0 || l == Block.LEAVES.id) && j > 0; --j) {
            ;
        }

        for (int i1 = 0; i1 < 128; ++i1) {
            int j1 = i + random.nextInt(8) - random.nextInt(8);
            int k1 = j + random.nextInt(4) - random.nextInt(4);
            int l1 = k + random.nextInt(8) - random.nextInt(8);

            if (world.isEmpty(j1, k1, l1) && Block.byId[this.a].f(world, j1, k1, l1)) {
                world.setTypeIdAndData(j1, k1, l1, this.a, this.b, 2);
            }
        }

        return true;
    }
}

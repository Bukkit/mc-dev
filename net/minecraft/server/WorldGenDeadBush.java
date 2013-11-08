package net.minecraft.server;

import java.util.Random;

public class WorldGenDeadBush extends WorldGenerator {

    private Block a;

    public WorldGenDeadBush(Block block) {
        this.a = block;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        Block block;

        while (((block = world.getType(i, j, k)).getMaterial() == Material.AIR || block.getMaterial() == Material.LEAVES) && j > 0) {
            --j;
        }

        for (int l = 0; l < 4; ++l) {
            int i1 = i + random.nextInt(8) - random.nextInt(8);
            int j1 = j + random.nextInt(4) - random.nextInt(4);
            int k1 = k + random.nextInt(8) - random.nextInt(8);

            if (world.isEmpty(i1, j1, k1) && this.a.j(world, i1, j1, k1)) {
                world.setTypeAndData(i1, j1, k1, this.a, 0, 2);
            }
        }

        return true;
    }
}

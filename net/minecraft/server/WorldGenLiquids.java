package net.minecraft.server;

import java.util.Random;

public class WorldGenLiquids extends WorldGenerator {

    private Block a;

    public WorldGenLiquids(Block block) {
        this.a = block;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        if (world.getType(i, j + 1, k) != Blocks.STONE) {
            return false;
        } else if (world.getType(i, j - 1, k) != Blocks.STONE) {
            return false;
        } else if (world.getType(i, j, k).getMaterial() != Material.AIR && world.getType(i, j, k) != Blocks.STONE) {
            return false;
        } else {
            int l = 0;

            if (world.getType(i - 1, j, k) == Blocks.STONE) {
                ++l;
            }

            if (world.getType(i + 1, j, k) == Blocks.STONE) {
                ++l;
            }

            if (world.getType(i, j, k - 1) == Blocks.STONE) {
                ++l;
            }

            if (world.getType(i, j, k + 1) == Blocks.STONE) {
                ++l;
            }

            int i1 = 0;

            if (world.isEmpty(i - 1, j, k)) {
                ++i1;
            }

            if (world.isEmpty(i + 1, j, k)) {
                ++i1;
            }

            if (world.isEmpty(i, j, k - 1)) {
                ++i1;
            }

            if (world.isEmpty(i, j, k + 1)) {
                ++i1;
            }

            if (l == 3 && i1 == 1) {
                world.setTypeAndData(i, j, k, this.a, 0, 2);
                world.d = true;
                this.a.a(world, i, j, k, random);
                world.d = false;
            }

            return true;
        }
    }
}

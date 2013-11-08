package net.minecraft.server;

import java.util.Random;

public class WorldGenHellLava extends WorldGenerator {

    private Block a;
    private boolean b;

    public WorldGenHellLava(Block block, boolean flag) {
        this.a = block;
        this.b = flag;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        if (world.getType(i, j + 1, k) != Blocks.NETHERRACK) {
            return false;
        } else if (world.getType(i, j, k).getMaterial() != Material.AIR && world.getType(i, j, k) != Blocks.NETHERRACK) {
            return false;
        } else {
            int l = 0;

            if (world.getType(i - 1, j, k) == Blocks.NETHERRACK) {
                ++l;
            }

            if (world.getType(i + 1, j, k) == Blocks.NETHERRACK) {
                ++l;
            }

            if (world.getType(i, j, k - 1) == Blocks.NETHERRACK) {
                ++l;
            }

            if (world.getType(i, j, k + 1) == Blocks.NETHERRACK) {
                ++l;
            }

            if (world.getType(i, j - 1, k) == Blocks.NETHERRACK) {
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

            if (world.isEmpty(i, j - 1, k)) {
                ++i1;
            }

            if (!this.b && l == 4 && i1 == 1 || l == 5) {
                world.setTypeAndData(i, j, k, this.a, 0, 2);
                world.d = true;
                this.a.a(world, i, j, k, random);
                world.d = false;
            }

            return true;
        }
    }
}

package net.minecraft.server;

import java.util.Random;

public class WorldGenLiquids extends WorldGenerator {

    private int a;

    public WorldGenLiquids(int i) {
        this.a = i;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        if (world.a(i, j + 1, k) != Block.STONE.bh) {
            return false;
        } else if (world.a(i, j - 1, k) != Block.STONE.bh) {
            return false;
        } else if (world.a(i, j, k) != 0 && world.a(i, j, k) != Block.STONE.bh) {
            return false;
        } else {
            int l = 0;

            if (world.a(i - 1, j, k) == Block.STONE.bh) {
                ++l;
            }

            if (world.a(i + 1, j, k) == Block.STONE.bh) {
                ++l;
            }

            if (world.a(i, j, k - 1) == Block.STONE.bh) {
                ++l;
            }

            if (world.a(i, j, k + 1) == Block.STONE.bh) {
                ++l;
            }

            int i1 = 0;

            if (world.e(i - 1, j, k)) {
                ++i1;
            }

            if (world.e(i + 1, j, k)) {
                ++i1;
            }

            if (world.e(i, j, k - 1)) {
                ++i1;
            }

            if (world.e(i, j, k + 1)) {
                ++i1;
            }

            if (l == 3 && i1 == 1) {
                world.d(i, j, k, this.a);
                world.a = true;
                Block.m[this.a].a(world, i, j, k, random);
                world.a = false;
            }

            return true;
        }
    }
}

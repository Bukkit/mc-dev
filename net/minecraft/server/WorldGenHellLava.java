package net.minecraft.server;

import java.util.Random;

public class WorldGenHellLava extends WorldGenerator {

    private int a;

    public WorldGenHellLava(int i) {
        this.a = i;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        if (world.a(i, j + 1, k) != Block.NETHERRACK.bi) {
            return false;
        } else if (world.a(i, j, k) != 0 && world.a(i, j, k) != Block.NETHERRACK.bi) {
            return false;
        } else {
            int l = 0;

            if (world.a(i - 1, j, k) == Block.NETHERRACK.bi) {
                ++l;
            }

            if (world.a(i + 1, j, k) == Block.NETHERRACK.bi) {
                ++l;
            }

            if (world.a(i, j, k - 1) == Block.NETHERRACK.bi) {
                ++l;
            }

            if (world.a(i, j, k + 1) == Block.NETHERRACK.bi) {
                ++l;
            }

            if (world.a(i, j - 1, k) == Block.NETHERRACK.bi) {
                ++l;
            }

            int i1 = 0;

            if (world.a(i - 1, j, k) == 0) {
                ++i1;
            }

            if (world.a(i + 1, j, k) == 0) {
                ++i1;
            }

            if (world.a(i, j, k - 1) == 0) {
                ++i1;
            }

            if (world.a(i, j, k + 1) == 0) {
                ++i1;
            }

            if (world.a(i, j - 1, k) == 0) {
                ++i1;
            }

            if (l == 4 && i1 == 1) {
                world.d(i, j, k, this.a);
                world.a = true;
                Block.n[this.a].a(world, i, j, k, random);
                world.a = false;
            }

            return true;
        }
    }
}

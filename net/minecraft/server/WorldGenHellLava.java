package net.minecraft.server;

import java.util.Random;

public class WorldGenHellLava extends WorldGenerator {

    private int a;
    private boolean b = false;

    public WorldGenHellLava(int i, boolean flag) {
        this.a = i;
        this.b = flag;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        if (world.getTypeId(i, j + 1, k) != Block.NETHERRACK.id) {
            return false;
        } else if (world.getTypeId(i, j, k) != 0 && world.getTypeId(i, j, k) != Block.NETHERRACK.id) {
            return false;
        } else {
            int l = 0;

            if (world.getTypeId(i - 1, j, k) == Block.NETHERRACK.id) {
                ++l;
            }

            if (world.getTypeId(i + 1, j, k) == Block.NETHERRACK.id) {
                ++l;
            }

            if (world.getTypeId(i, j, k - 1) == Block.NETHERRACK.id) {
                ++l;
            }

            if (world.getTypeId(i, j, k + 1) == Block.NETHERRACK.id) {
                ++l;
            }

            if (world.getTypeId(i, j - 1, k) == Block.NETHERRACK.id) {
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
                world.setTypeIdAndData(i, j, k, this.a, 0, 2);
                world.d = true;
                Block.byId[this.a].a(world, i, j, k, random);
                world.d = false;
            }

            return true;
        }
    }
}

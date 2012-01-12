package net.minecraft.server;

import java.util.Random;

public class BlockLadder extends Block {

    protected BlockLadder(int i, int j) {
        super(i, j, Material.ORIENTABLE);
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int c() {
        return 8;
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return world.e(i - 1, j, k) ? true : (world.e(i + 1, j, k) ? true : (world.e(i, j, k - 1) ? true : world.e(i, j, k + 1)));
    }

    public void postPlace(World world, int i, int j, int k, int l) {
        int i1 = world.getData(i, j, k);

        if ((i1 == 0 || l == 2) && world.e(i, j, k + 1)) {
            i1 = 2;
        }

        if ((i1 == 0 || l == 3) && world.e(i, j, k - 1)) {
            i1 = 3;
        }

        if ((i1 == 0 || l == 4) && world.e(i + 1, j, k)) {
            i1 = 4;
        }

        if ((i1 == 0 || l == 5) && world.e(i - 1, j, k)) {
            i1 = 5;
        }

        world.setData(i, j, k, i1);
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        int i1 = world.getData(i, j, k);
        boolean flag = false;

        if (i1 == 2 && world.e(i, j, k + 1)) {
            flag = true;
        }

        if (i1 == 3 && world.e(i, j, k - 1)) {
            flag = true;
        }

        if (i1 == 4 && world.e(i + 1, j, k)) {
            flag = true;
        }

        if (i1 == 5 && world.e(i - 1, j, k)) {
            flag = true;
        }

        if (!flag) {
            this.b(world, i, j, k, i1, 0);
            world.setTypeId(i, j, k, 0);
        }

        super.doPhysics(world, i, j, k, l);
    }

    public int a(Random random) {
        return 1;
    }
}

package net.minecraft.server;

import java.util.Random;

public class BlockLadder extends Block {

    protected BlockLadder(int i, int j) {
        super(i, j, Material.ORIENTABLE);
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        int l = world.getData(i, j, k);
        float f = 0.125F;

        if (l == 2) {
            this.a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        }

        if (l == 3) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        }

        if (l == 4) {
            this.a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (l == 5) {
            this.a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        }

        return super.e(world, i, j, k);
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
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
            this.g(world, i, j, k, i1);
            world.setTypeId(i, j, k, 0);
        }

        super.doPhysics(world, i, j, k, l);
    }

    public int a(Random random) {
        return 1;
    }
}

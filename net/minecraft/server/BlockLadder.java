package net.minecraft.server;

import java.util.Random;

public class BlockLadder extends Block {

    protected BlockLadder(int i, int j) {
        super(i, j, Material.ORIENTABLE);
        this.a(CreativeModeTab.c);
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        this.updateShape(world, i, j, k);
        return super.e(world, i, j, k);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        this.d(iblockaccess.getData(i, j, k));
    }

    public void d(int i) {
        float f = 0.125F;

        if (i == 2) {
            this.a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        }

        if (i == 3) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        }

        if (i == 4) {
            this.a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (i == 5) {
            this.a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        }
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int d() {
        return 8;
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return world.t(i - 1, j, k) ? true : (world.t(i + 1, j, k) ? true : (world.t(i, j, k - 1) ? true : world.t(i, j, k + 1)));
    }

    public int getPlacedData(World world, int i, int j, int k, int l, float f, float f1, float f2, int i1) {
        int j1 = i1;

        if ((i1 == 0 || l == 2) && world.t(i, j, k + 1)) {
            j1 = 2;
        }

        if ((j1 == 0 || l == 3) && world.t(i, j, k - 1)) {
            j1 = 3;
        }

        if ((j1 == 0 || l == 4) && world.t(i + 1, j, k)) {
            j1 = 4;
        }

        if ((j1 == 0 || l == 5) && world.t(i - 1, j, k)) {
            j1 = 5;
        }

        return j1;
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        int i1 = world.getData(i, j, k);
        boolean flag = false;

        if (i1 == 2 && world.t(i, j, k + 1)) {
            flag = true;
        }

        if (i1 == 3 && world.t(i, j, k - 1)) {
            flag = true;
        }

        if (i1 == 4 && world.t(i + 1, j, k)) {
            flag = true;
        }

        if (i1 == 5 && world.t(i - 1, j, k)) {
            flag = true;
        }

        if (!flag) {
            this.c(world, i, j, k, i1, 0);
            world.setTypeId(i, j, k, 0);
        }

        super.doPhysics(world, i, j, k, l);
    }

    public int a(Random random) {
        return 1;
    }
}

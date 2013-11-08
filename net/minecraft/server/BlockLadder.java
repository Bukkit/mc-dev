package net.minecraft.server;

import java.util.Random;

public class BlockLadder extends Block {

    protected BlockLadder() {
        super(Material.ORIENTABLE);
        this.a(CreativeModeTab.c);
    }

    public AxisAlignedBB a(World world, int i, int j, int k) {
        this.updateShape(world, i, j, k);
        return super.a(world, i, j, k);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        this.b(iblockaccess.getData(i, j, k));
    }

    public void b(int i) {
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

    public boolean d() {
        return false;
    }

    public int b() {
        return 8;
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return world.getType(i - 1, j, k).r() ? true : (world.getType(i + 1, j, k).r() ? true : (world.getType(i, j, k - 1).r() ? true : world.getType(i, j, k + 1).r()));
    }

    public int getPlacedData(World world, int i, int j, int k, int l, float f, float f1, float f2, int i1) {
        int j1 = i1;

        if ((i1 == 0 || l == 2) && world.getType(i, j, k + 1).r()) {
            j1 = 2;
        }

        if ((j1 == 0 || l == 3) && world.getType(i, j, k - 1).r()) {
            j1 = 3;
        }

        if ((j1 == 0 || l == 4) && world.getType(i + 1, j, k).r()) {
            j1 = 4;
        }

        if ((j1 == 0 || l == 5) && world.getType(i - 1, j, k).r()) {
            j1 = 5;
        }

        return j1;
    }

    public void doPhysics(World world, int i, int j, int k, Block block) {
        int l = world.getData(i, j, k);
        boolean flag = false;

        if (l == 2 && world.getType(i, j, k + 1).r()) {
            flag = true;
        }

        if (l == 3 && world.getType(i, j, k - 1).r()) {
            flag = true;
        }

        if (l == 4 && world.getType(i + 1, j, k).r()) {
            flag = true;
        }

        if (l == 5 && world.getType(i - 1, j, k).r()) {
            flag = true;
        }

        if (!flag) {
            this.b(world, i, j, k, l, 0);
            world.setAir(i, j, k);
        }

        super.doPhysics(world, i, j, k, block);
    }

    public int a(Random random) {
        return 1;
    }
}

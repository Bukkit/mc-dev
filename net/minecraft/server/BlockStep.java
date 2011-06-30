package net.minecraft.server;

import java.util.Random;

public class BlockStep extends Block {

    public static final String[] a = new String[] { "stone", "sand", "wood", "cobble"};
    private boolean b;

    public BlockStep(int i, boolean flag) {
        super(i, 6, Material.STONE);
        this.b = flag;
        if (!flag) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }

        this.f(255);
    }

    public int a(int i, int j) {
        return j == 0 ? (i <= 1 ? 6 : 5) : (j == 1 ? (i == 0 ? 208 : (i == 1 ? 176 : 192)) : (j == 2 ? 4 : (j == 3 ? 16 : 6)));
    }

    public int a(int i) {
        return this.a(i, 0);
    }

    public boolean a() {
        return this.b;
    }

    public void c(World world, int i, int j, int k) {
        if (this != Block.STEP) {
            super.c(world, i, j, k);
        }

        int l = world.getTypeId(i, j - 1, k);
        int i1 = world.getData(i, j, k);
        int j1 = world.getData(i, j - 1, k);

        if (i1 == j1) {
            if (l == STEP.id) {
                world.setTypeId(i, j, k, 0);
                world.setTypeIdAndData(i, j - 1, k, Block.DOUBLE_STEP.id, i1);
            }
        }
    }

    public int a(int i, Random random) {
        return Block.STEP.id;
    }

    public int a(Random random) {
        return this.b ? 2 : 1;
    }

    protected int a_(int i) {
        return i;
    }

    public boolean b() {
        return this.b;
    }
}

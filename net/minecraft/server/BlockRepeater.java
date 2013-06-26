package net.minecraft.server;

import java.util.Random;

public class BlockRepeater extends BlockDiodeAbstract {

    public static final double[] b = new double[] { -0.0625D, 0.0625D, 0.1875D, 0.3125D};
    private static final int[] c = new int[] { 1, 2, 3, 4};

    protected BlockRepeater(int i, boolean flag) {
        super(i, flag);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        int i1 = world.getData(i, j, k);
        int j1 = (i1 & 12) >> 2;

        j1 = j1 + 1 << 2 & 12;
        world.setData(i, j, k, j1 | i1 & 3, 3);
        return true;
    }

    protected int k_(int i) {
        return c[(i & 12) >> 2] * 2;
    }

    protected BlockDiodeAbstract i() {
        return Block.DIODE_ON;
    }

    protected BlockDiodeAbstract j() {
        return Block.DIODE_OFF;
    }

    public int getDropType(int i, Random random, int j) {
        return Item.DIODE.id;
    }

    public int d() {
        return 15;
    }

    public boolean e(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return this.f(iblockaccess, i, j, k, l) > 0;
    }

    protected boolean e(int i) {
        return f(i);
    }

    public void remove(World world, int i, int j, int k, int l, int i1) {
        super.remove(world, i, j, k, l, i1);
        this.h_(world, i, j, k);
    }
}

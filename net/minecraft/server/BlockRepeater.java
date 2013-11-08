package net.minecraft.server;

import java.util.Random;

public class BlockRepeater extends BlockDiodeAbstract {

    public static final double[] b = new double[] { -0.0625D, 0.0625D, 0.1875D, 0.3125D};
    private static final int[] M = new int[] { 1, 2, 3, 4};

    protected BlockRepeater(boolean flag) {
        super(flag);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        int i1 = world.getData(i, j, k);
        int j1 = (i1 & 12) >> 2;

        j1 = j1 + 1 << 2 & 12;
        world.setData(i, j, k, j1 | i1 & 3, 3);
        return true;
    }

    protected int b(int i) {
        return M[(i & 12) >> 2] * 2;
    }

    protected BlockDiodeAbstract e() {
        return Blocks.DIODE_ON;
    }

    protected BlockDiodeAbstract i() {
        return Blocks.DIODE_OFF;
    }

    public Item getDropType(int i, Random random, int j) {
        return Items.DIODE;
    }

    public int b() {
        return 15;
    }

    public boolean g(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return this.h(iblockaccess, i, j, k, l) > 0;
    }

    protected boolean a(Block block) {
        return d(block);
    }

    public void remove(World world, int i, int j, int k, Block block, int l) {
        super.remove(world, i, j, k, block, l);
        this.e(world, i, j, k);
    }
}

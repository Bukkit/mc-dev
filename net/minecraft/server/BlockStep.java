package net.minecraft.server;

import java.util.Random;

public class BlockStep extends BlockStepAbstract {

    public static final String[] a = new String[] { "stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick", "netherBrick"};

    public BlockStep(int i, boolean flag) {
        super(i, flag, Material.STONE);
        this.a(CreativeModeTab.b);
    }

    public int a(int i, int j) {
        int k = j & 7;

        return k == 0 ? (i <= 1 ? 6 : 5) : (k == 1 ? (i == 0 ? 208 : (i == 1 ? 176 : 192)) : (k == 2 ? 4 : (k == 3 ? 16 : (k == 4 ? Block.BRICK.textureId : (k == 5 ? Block.SMOOTH_BRICK.textureId : (k == 6 ? Block.NETHER_BRICK.textureId : 6))))));
    }

    public int a(int i) {
        return this.a(i, 0);
    }

    public int getDropType(int i, Random random, int j) {
        return Block.STEP.id;
    }

    protected ItemStack f_(int i) {
        return new ItemStack(Block.STEP.id, 2, i & 7);
    }

    public String d(int i) {
        if (i < 0 || i >= a.length) {
            i = 0;
        }

        return super.a() + "." + a[i];
    }
}

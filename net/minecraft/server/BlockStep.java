package net.minecraft.server;

import java.util.Random;

public class BlockStep extends BlockStepAbstract {

    public static final String[] b = new String[] { "stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick", "netherBrick", "quartz"};

    public BlockStep(int i, boolean flag) {
        super(i, flag, Material.STONE);
        this.a(CreativeModeTab.b);
    }

    public int getDropType(int i, Random random, int j) {
        return Block.STEP.id;
    }

    protected ItemStack c_(int i) {
        return new ItemStack(Block.STEP.id, 2, i & 7);
    }

    public String c(int i) {
        if (i < 0 || i >= b.length) {
            i = 0;
        }

        return super.a() + "." + b[i];
    }
}

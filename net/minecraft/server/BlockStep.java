package net.minecraft.server;

import java.util.Random;

public class BlockStep extends BlockStepAbstract {

    public static final String[] b = new String[] { "stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick", "netherBrick", "quartz"};

    public BlockStep(boolean flag) {
        super(flag, Material.STONE);
        this.a(CreativeModeTab.b);
    }

    public Item getDropType(int i, Random random, int j) {
        return Item.getItemOf(Blocks.STEP);
    }

    protected ItemStack j(int i) {
        return new ItemStack(Item.getItemOf(Blocks.STEP), 2, i & 7);
    }

    public String b(int i) {
        if (i < 0 || i >= b.length) {
            i = 0;
        }

        return super.a() + "." + b[i];
    }
}

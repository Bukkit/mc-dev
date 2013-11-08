package net.minecraft.server;

import java.util.Random;

public class BlockWoodStep extends BlockStepAbstract {

    public static final String[] b = new String[] { "oak", "spruce", "birch", "jungle", "acacia", "big_oak"};

    public BlockWoodStep(boolean flag) {
        super(flag, Material.WOOD);
        this.a(CreativeModeTab.b);
    }

    public Item getDropType(int i, Random random, int j) {
        return Item.getItemOf(Blocks.WOOD_STEP);
    }

    protected ItemStack j(int i) {
        return new ItemStack(Item.getItemOf(Blocks.WOOD_STEP), 2, i & 7);
    }

    public String b(int i) {
        if (i < 0 || i >= b.length) {
            i = 0;
        }

        return super.a() + "." + b[i];
    }
}

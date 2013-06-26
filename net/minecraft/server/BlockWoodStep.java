package net.minecraft.server;

import java.util.Random;

public class BlockWoodStep extends BlockStepAbstract {

    public static final String[] b = new String[] { "oak", "spruce", "birch", "jungle"};

    public BlockWoodStep(int i, boolean flag) {
        super(i, flag, Material.WOOD);
        this.a(CreativeModeTab.b);
    }

    public int getDropType(int i, Random random, int j) {
        return Block.WOOD_STEP.id;
    }

    protected ItemStack d_(int i) {
        return new ItemStack(Block.WOOD_STEP.id, 2, i & 7);
    }

    public String c(int i) {
        if (i < 0 || i >= b.length) {
            i = 0;
        }

        return super.a() + "." + b[i];
    }
}

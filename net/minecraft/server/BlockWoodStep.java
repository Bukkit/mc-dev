package net.minecraft.server;

import java.util.Random;

public class BlockWoodStep extends BlockStepAbstract {

    public static final String[] a = new String[] { "oak", "spruce", "birch", "jungle"};

    public BlockWoodStep(int i, boolean flag) {
        super(i, flag, Material.WOOD);
        this.a(CreativeModeTab.b);
    }

    public int a(int i, int j) {
        switch (j & 7) {
        case 1:
            return 198;

        case 2:
            return 214;

        case 3:
            return 199;

        default:
            return 4;
        }
    }

    public int a(int i) {
        return this.a(i, 0);
    }

    public int getDropType(int i, Random random, int j) {
        return Block.WOOD_STEP.id;
    }

    protected ItemStack f_(int i) {
        return new ItemStack(Block.WOOD_STEP.id, 2, i & 7);
    }

    public String d(int i) {
        if (i < 0 || i >= a.length) {
            i = 0;
        }

        return super.a() + "." + a[i];
    }
}

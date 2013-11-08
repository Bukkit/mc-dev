package net.minecraft.server;

import java.util.Random;

public class BlockStainedGlass extends BlockHalfTransparent {

    private static final IIcon[] a = new IIcon[16];

    public BlockStainedGlass(Material material) {
        super("glass", material, false);
        this.a(CreativeModeTab.b);
    }

    public int getDropData(int i) {
        return i;
    }

    public int a(Random random) {
        return 0;
    }

    protected boolean E() {
        return true;
    }

    public boolean d() {
        return false;
    }
}

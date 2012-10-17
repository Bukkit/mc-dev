package net.minecraft.server;

import java.util.Random;

public class BlockGlass extends BlockHalfTransparant {

    public BlockGlass(int i, int j, Material material, boolean flag) {
        super(i, j, material, flag);
        this.a(CreativeModeTab.b);
    }

    public int a(Random random) {
        return 0;
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    protected boolean s_() {
        return true;
    }
}

package net.minecraft.server;

import java.util.Random;

public class BlockGlass extends BlockHalfTransparant {

    public BlockGlass(int i, int j, Material material, boolean flag) {
        super(i, j, material, flag);
    }

    public int a(Random random) {
        return 0;
    }
}

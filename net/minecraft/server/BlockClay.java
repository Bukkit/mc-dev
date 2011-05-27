package net.minecraft.server;

import java.util.Random;

public class BlockClay extends Block {

    public BlockClay(int i, int j) {
        super(i, j, Material.CLAY);
    }

    public int a(int i, Random random) {
        return Item.CLAY_BALL.id;
    }

    public int a(Random random) {
        return 4;
    }
}

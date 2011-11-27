package net.minecraft.server;

import java.util.Random;

public class BlockClay extends Block {

    public BlockClay(int i, int j) {
        super(i, j, Material.CLAY);
    }

    public int getDropType(int i, Random random, int j) {
        return Item.CLAY_BALL.id;
    }

    public int a(Random random) {
        return 4;
    }
}

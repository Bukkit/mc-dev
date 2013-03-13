package net.minecraft.server;

import java.util.Random;

public class BlockClay extends Block {

    public BlockClay(int i) {
        super(i, Material.CLAY);
        this.a(CreativeModeTab.b);
    }

    public int getDropType(int i, Random random, int j) {
        return Item.CLAY_BALL.id;
    }

    public int a(Random random) {
        return 4;
    }
}

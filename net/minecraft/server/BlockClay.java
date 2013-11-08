package net.minecraft.server;

import java.util.Random;

public class BlockClay extends Block {

    public BlockClay() {
        super(Material.CLAY);
        this.a(CreativeModeTab.b);
    }

    public Item getDropType(int i, Random random, int j) {
        return Items.CLAY_BALL;
    }

    public int a(Random random) {
        return 4;
    }
}

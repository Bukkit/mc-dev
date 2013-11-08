package net.minecraft.server;

import java.util.Random;

public class BlockObsidian extends BlockStone {

    public BlockObsidian() {}

    public int a(Random random) {
        return 1;
    }

    public Item getDropType(int i, Random random, int j) {
        return Item.getItemOf(Blocks.OBSIDIAN);
    }

    public MaterialMapColor f(int i) {
        return MaterialMapColor.J;
    }
}

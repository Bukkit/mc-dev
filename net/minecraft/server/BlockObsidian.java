package net.minecraft.server;

import java.util.Random;

public class BlockObsidian extends BlockStone {

    public BlockObsidian(int i, int j) {
        super(i, j);
    }

    public int a(Random random) {
        return 1;
    }

    public int getDropType(int i, Random random, int j) {
        return Block.OBSIDIAN.id;
    }
}

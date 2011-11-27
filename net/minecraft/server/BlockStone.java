package net.minecraft.server;

import java.util.Random;

public class BlockStone extends Block {

    public BlockStone(int i, int j) {
        super(i, j, Material.STONE);
    }

    public int getDropType(int i, Random random, int j) {
        return Block.COBBLESTONE.id;
    }
}

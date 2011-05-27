package net.minecraft.server;

import java.util.Random;

public class BlockStone extends Block {

    public BlockStone(int i, int j) {
        super(i, j, Material.STONE);
    }

    public int a(int i, Random random) {
        return Block.COBBLESTONE.id;
    }
}

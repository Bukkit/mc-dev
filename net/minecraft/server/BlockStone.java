package net.minecraft.server;

import java.util.Random;

public class BlockStone extends Block {

    public BlockStone() {
        super(Material.STONE);
        this.a(CreativeModeTab.b);
    }

    public Item getDropType(int i, Random random, int j) {
        return Item.getItemOf(Blocks.COBBLESTONE);
    }
}

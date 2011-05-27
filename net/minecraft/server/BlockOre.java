package net.minecraft.server;

import java.util.Random;

public class BlockOre extends Block {

    public BlockOre(int i, int j) {
        super(i, j, Material.d);
    }

    public int a(int i, Random random) {
        return this.bh == Block.COAL_ORE.bh ? Item.COAL.aW : (this.bh == Block.DIAMOND_ORE.bh ? Item.DIAMOND.aW : this.bh);
    }

    public int a(Random random) {
        return 1;
    }
}

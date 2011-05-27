package net.minecraft.server;

import java.util.Random;

public class BlockOre extends Block {

    public BlockOre(int i, int j) {
        super(i, j, Material.d);
    }

    public int a(int i, Random random) {
        return this.bc == Block.COAL_ORE.bc ? Item.COAL.aS : (this.bc == Block.DIAMOND_ORE.bc ? Item.DIAMOND.aS : this.bc);
    }

    public int a(Random random) {
        return 1;
    }
}

package net.minecraft.server;

import java.util.Random;

public class BlockOre extends Block {

    public BlockOre(int i, int j) {
        super(i, j, Material.d);
    }

    public int a(int i, Random random) {
        return this.bi == Block.COAL_ORE.bi ? Item.COAL.ba : (this.bi == Block.DIAMOND_ORE.bi ? Item.DIAMOND.ba : (this.bi == Block.LAPIS_ORE.bi ? Item.INK_SACK.ba : this.bi));
    }

    public int a(Random random) {
        return 1;
    }

    protected int b(int i) {
        return this.bi == Block.LAPIS_ORE.bi ? 4 : 0;
    }
}

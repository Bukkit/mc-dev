package net.minecraft.server;

import java.util.Random;

public class BlockOre extends Block {

    public BlockOre(int i, int j) {
        super(i, j, Material.STONE);
    }

    public int a(int i, Random random) {
        return this.id == Block.COAL_ORE.id ? Item.COAL.id : (this.id == Block.DIAMOND_ORE.id ? Item.DIAMOND.id : (this.id == Block.LAPIS_ORE.id ? Item.INK_SACK.id : this.id));
    }

    public int a(Random random) {
        return this.id == Block.LAPIS_ORE.id ? 4 + random.nextInt(5) : 1;
    }

    protected int a_(int i) {
        return this.id == Block.LAPIS_ORE.id ? 4 : 0;
    }
}

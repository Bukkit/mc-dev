package net.minecraft.server;

public class ItemPickaxe extends ItemTool {

    private static Block[] bk = new Block[] { Block.COBBLESTONE, Block.DOUBLE_STEP, Block.STEP, Block.STONE, Block.SANDSTONE, Block.MOSSY_COBBLESTONE, Block.IRON_ORE, Block.IRON_BLOCK, Block.COAL_ORE, Block.GOLD_BLOCK, Block.GOLD_ORE, Block.DIAMOND_ORE, Block.DIAMOND_BLOCK, Block.ICE, Block.NETHERRACK, Block.LAPIS_ORE, Block.LAPIS_BLOCK};

    protected ItemPickaxe(int i, EnumToolMaterial enumtoolmaterial) {
        super(i, 2, enumtoolmaterial, bk);
    }

    public boolean a(Block block) {
        return block == Block.OBSIDIAN ? this.a.d() == 3 : (block != Block.DIAMOND_BLOCK && block != Block.DIAMOND_ORE ? (block != Block.GOLD_BLOCK && block != Block.GOLD_ORE ? (block != Block.IRON_BLOCK && block != Block.IRON_ORE ? (block != Block.LAPIS_BLOCK && block != Block.LAPIS_ORE ? (block != Block.REDSTONE_ORE && block != Block.GLOWING_REDSTONE_ORE ? (block.material == Material.STONE ? true : block.material == Material.ORE) : this.a.d() >= 2) : this.a.d() >= 1) : this.a.d() >= 1) : this.a.d() >= 2) : this.a.d() >= 2);
    }
}

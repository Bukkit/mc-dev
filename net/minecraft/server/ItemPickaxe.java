package net.minecraft.server;

public class ItemPickaxe extends ItemTool {

    private static Block[] aX = new Block[] { Block.COBBLESTONE, Block.DOUBLE_STEP, Block.STEP, Block.STONE, Block.MOSSY_COBBLESTONE, Block.IRON_ORE, Block.IRON_BLOCK, Block.COAL_ORE, Block.GOLD_BLOCK, Block.GOLD_ORE, Block.DIAMOND_ORE, Block.DIAMOND_BLOCK, Block.ICE};
    private int aY;

    public ItemPickaxe(int i, int j) {
        super(i, 2, j, aX);
        this.aY = j;
    }

    public boolean a(Block block) {
        return block == Block.OBSIDIAN ? this.aY == 3 : (block != Block.DIAMOND_BLOCK && block != Block.DIAMOND_ORE ? (block != Block.GOLD_BLOCK && block != Block.GOLD_ORE ? (block != Block.IRON_BLOCK && block != Block.IRON_ORE ? (block != Block.REDSTONE_ORE && block != Block.GLOWING_REDSTONE_ORE ? (block.bn == Material.d ? true : block.bn == Material.e) : this.aY >= 2) : this.aY >= 1) : this.aY >= 2) : this.aY >= 2);
    }
}

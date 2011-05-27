package net.minecraft.server;

public class ItemPickaxe extends ItemTool {

    private static Block[] bb = new Block[] { Block.COBBLESTONE, Block.DOUBLE_STEP, Block.STEP, Block.STONE, Block.MOSSY_COBBLESTONE, Block.IRON_ORE, Block.IRON_BLOCK, Block.COAL_ORE, Block.GOLD_BLOCK, Block.GOLD_ORE, Block.DIAMOND_ORE, Block.DIAMOND_BLOCK, Block.ICE, Block.NETHERRACK};
    private int bc;

    public ItemPickaxe(int i, int j) {
        super(i, 2, j, bb);
        this.bc = j;
    }

    public boolean a(Block block) {
        return block == Block.OBSIDIAN ? this.bc == 3 : (block != Block.DIAMOND_BLOCK && block != Block.DIAMOND_ORE ? (block != Block.GOLD_BLOCK && block != Block.GOLD_ORE ? (block != Block.IRON_BLOCK && block != Block.IRON_ORE ? (block != Block.REDSTONE_ORE && block != Block.GLOWING_REDSTONE_ORE ? (block.bt == Material.d ? true : block.bt == Material.e) : this.bc >= 2) : this.bc >= 1) : this.bc >= 2) : this.bc >= 2);
    }
}

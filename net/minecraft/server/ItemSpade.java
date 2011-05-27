package net.minecraft.server;

public class ItemSpade extends ItemTool {

    private static Block[] bb = new Block[] { Block.GRASS, Block.DIRT, Block.SAND, Block.GRAVEL, Block.SNOW, Block.SNOW_BLOCK, Block.CLAY};

    public ItemSpade(int i, int j) {
        super(i, 1, j, bb);
    }

    public boolean a(Block block) {
        return block == Block.SNOW ? true : block == Block.SNOW_BLOCK;
    }
}

package net.minecraft.server;

public class ItemSpade extends ItemTool {

    private static Block[] bg = new Block[] { Block.GRASS, Block.DIRT, Block.SAND, Block.GRAVEL, Block.SNOW, Block.SNOW_BLOCK, Block.CLAY};

    public ItemSpade(int i, EnumToolMaterial enumtoolmaterial) {
        super(i, 1, enumtoolmaterial, bg);
    }

    public boolean a(Block block) {
        return block == Block.SNOW ? true : block == Block.SNOW_BLOCK;
    }
}

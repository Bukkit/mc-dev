package net.minecraft.server;

public class ItemAxe extends ItemTool {

    private static Block[] bg = new Block[] { Block.WOOD, Block.BOOKSHELF, Block.LOG, Block.CHEST};

    protected ItemAxe(int i, EnumToolMaterial enumtoolmaterial) {
        super(i, 3, enumtoolmaterial, bg);
    }
}

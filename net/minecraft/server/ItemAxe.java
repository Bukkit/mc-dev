package net.minecraft.server;

public class ItemAxe extends ItemTool {

    private static Block[] aX = new Block[] { Block.WOOD, Block.BOOKSHELF, Block.LOG, Block.CHEST};

    public ItemAxe(int i, int j) {
        super(i, 3, j, aX);
    }
}

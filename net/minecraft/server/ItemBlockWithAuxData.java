package net.minecraft.server;

public class ItemBlockWithAuxData extends ItemBlock {

    private Block b;

    public ItemBlockWithAuxData(Block block, Block block1) {
        super(block);
        this.b = block1;
        this.setMaxDurability(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }
}

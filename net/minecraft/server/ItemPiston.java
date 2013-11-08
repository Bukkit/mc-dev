package net.minecraft.server;

public class ItemPiston extends ItemBlock {

    public ItemPiston(Block block) {
        super(block);
    }

    public int filterData(int i) {
        return 7;
    }
}

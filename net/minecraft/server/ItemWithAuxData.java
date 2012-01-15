package net.minecraft.server;

public class ItemWithAuxData extends ItemBlock {

    private Block a;

    public ItemWithAuxData(int i, Block block) {
        super(i);
        this.a = block;
        this.setMaxDurability(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }
}

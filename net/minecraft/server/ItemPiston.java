package net.minecraft.server;

public class ItemPiston extends ItemBlock {

    public ItemPiston(int i) {
        super(i);
    }

    public int filterData(int i) {
        return 7;
    }
}

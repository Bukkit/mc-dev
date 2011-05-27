package net.minecraft.server;

public class ItemStep extends ItemBlock {

    public ItemStep(int i) {
        super(i);
        this.d(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }
}

package net.minecraft.server;

public class ItemLog extends ItemBlock {

    public ItemLog(int i) {
        super(i);
        this.d(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }
}

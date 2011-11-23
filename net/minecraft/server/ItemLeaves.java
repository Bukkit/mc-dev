package net.minecraft.server;

public class ItemLeaves extends ItemBlock {

    public ItemLeaves(int i) {
        super(i);
        this.f(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i | 4;
    }
}

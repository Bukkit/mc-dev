package net.minecraft.server;

public class ItemSapling extends ItemBlock {

    public ItemSapling(int i) {
        super(i);
        this.f(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }
}

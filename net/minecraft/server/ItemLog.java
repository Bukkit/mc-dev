package net.minecraft.server;

public class ItemLog extends ItemBlock {

    private Block a;

    public ItemLog(int i, Block block) {
        super(i);
        this.a = block;
        this.d(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }
}

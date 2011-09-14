package net.minecraft.server;

public class ItemColoredBlock extends ItemBlock {

    private final Block a;

    public ItemColoredBlock(int i, boolean flag) {
        super(i);
        this.a = Block.byId[this.a()];
        if (flag) {
            this.d(0);
            this.a(true);
        }
    }

    public int a(int i) {
        return i;
    }
}

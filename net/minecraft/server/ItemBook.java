package net.minecraft.server;

public class ItemBook extends Item {

    public ItemBook(int i) {
        super(i);
    }

    public boolean e_(ItemStack itemstack) {
        return itemstack.count == 1;
    }

    public int c() {
        return 1;
    }
}

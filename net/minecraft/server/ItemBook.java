package net.minecraft.server;

public class ItemBook extends Item {

    public ItemBook() {}

    public boolean e_(ItemStack itemstack) {
        return itemstack.count == 1;
    }

    public int c() {
        return 1;
    }
}

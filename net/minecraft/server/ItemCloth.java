package net.minecraft.server;

public class ItemCloth extends ItemBlock {

    public ItemCloth(int i) {
        super(i);
        this.d(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }

    public String a(ItemStack itemstack) {
        return super.b() + "." + ItemDye.a[BlockCloth.c(itemstack.getData())];
    }
}

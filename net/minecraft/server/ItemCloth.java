package net.minecraft.server;

public class ItemCloth extends ItemBlock {

    public ItemCloth(int i) {
        super(i);
        this.setMaxDurability(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }

    public String d(ItemStack itemstack) {
        return super.getName() + "." + ItemDye.a[BlockCloth.g_(itemstack.getData())];
    }
}

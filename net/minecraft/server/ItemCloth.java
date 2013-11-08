package net.minecraft.server;

public class ItemCloth extends ItemBlock {

    public ItemCloth(Block block) {
        super(block);
        this.setMaxDurability(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }

    public String a(ItemStack itemstack) {
        return super.getName() + "." + ItemDye.a[BlockCloth.b(itemstack.getData())];
    }
}

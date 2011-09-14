package net.minecraft.server;

public class ItemCoal extends Item {

    public ItemCoal(int i) {
        super(i);
        this.a(true);
        this.d(0);
    }

    public String a(ItemStack itemstack) {
        return itemstack.getData() == 1 ? "item.charcoal" : "item.coal";
    }
}

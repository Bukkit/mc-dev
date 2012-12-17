package net.minecraft.server;

public class ItemCoal extends Item {

    public ItemCoal(int i) {
        super(i);
        this.a(true);
        this.setMaxDurability(0);
        this.a(CreativeModeTab.l);
    }

    public String d(ItemStack itemstack) {
        return itemstack.getData() == 1 ? "item.charcoal" : "item.coal";
    }
}

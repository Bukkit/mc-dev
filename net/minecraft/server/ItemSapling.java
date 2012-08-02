package net.minecraft.server;

public class ItemSapling extends ItemBlock {

    public ItemSapling(int i) {
        super(i);
        this.setMaxDurability(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }

    public String c(ItemStack itemstack) {
        int i = itemstack.getData();

        if (i < 0 || i >= BlockSapling.a.length) {
            i = 0;
        }

        return super.getName() + "." + BlockSapling.a[i];
    }
}

package net.minecraft.server;

public class ItemLeaves extends ItemBlock {

    public ItemLeaves(int i) {
        super(i);
        this.setMaxDurability(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i | 4;
    }

    public String c(ItemStack itemstack) {
        int i = itemstack.getData();

        if (i < 0 || i >= BlockLeaves.a.length) {
            i = 0;
        }

        return super.getName() + "." + BlockLeaves.a[i];
    }
}

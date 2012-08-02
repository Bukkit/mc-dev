package net.minecraft.server;

public class ItemWood extends ItemBlock {

    private Block a;

    public ItemWood(int i, Block block) {
        super(i);
        this.a = block;
        this.setMaxDurability(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }

    public String c(ItemStack itemstack) {
        int i = itemstack.getData();

        if (i < 0 || i >= BlockWood.a.length) {
            i = 0;
        }

        return super.getName() + "." + BlockWood.a[i];
    }
}

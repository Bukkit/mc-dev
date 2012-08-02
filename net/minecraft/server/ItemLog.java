package net.minecraft.server;

public class ItemLog extends ItemBlock {

    private Block a;

    public ItemLog(int i, Block block) {
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

        if (i < 0 || i >= BlockLog.a.length) {
            i = 0;
        }

        return super.getName() + "." + BlockLog.a[i];
    }
}

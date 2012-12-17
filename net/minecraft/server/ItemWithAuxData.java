package net.minecraft.server;

public class ItemWithAuxData extends ItemBlock {

    private final Block a;
    private String[] b;

    public ItemWithAuxData(int i, boolean flag) {
        super(i);
        this.a = Block.byId[this.g()];
        if (flag) {
            this.setMaxDurability(0);
            this.a(true);
        }
    }

    public int filterData(int i) {
        return i;
    }

    public ItemWithAuxData a(String[] astring) {
        this.b = astring;
        return this;
    }

    public String d(ItemStack itemstack) {
        if (this.b == null) {
            return super.d(itemstack);
        } else {
            int i = itemstack.getData();

            return i >= 0 && i < this.b.length ? super.d(itemstack) + "." + this.b[i] : super.d(itemstack);
        }
    }
}

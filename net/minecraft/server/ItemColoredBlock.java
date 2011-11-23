package net.minecraft.server;

public class ItemColoredBlock extends ItemBlock {

    private final Block a;
    private String[] b;

    public ItemColoredBlock(int i, boolean flag) {
        super(i);
        this.a = Block.byId[this.a()];
        if (flag) {
            this.f(0);
            this.a(true);
        }
    }

    public int filterData(int i) {
        return i;
    }

    public ItemColoredBlock a(String[] astring) {
        this.b = astring;
        return this;
    }

    public String a(ItemStack itemstack) {
        if (this.b == null) {
            return super.a(itemstack);
        } else {
            int i = itemstack.getData();

            return i >= 0 && i < this.b.length ? super.a(itemstack) + "." + this.b[i] : super.a(itemstack);
        }
    }
}

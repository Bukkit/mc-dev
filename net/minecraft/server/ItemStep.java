package net.minecraft.server;

public class ItemStep extends ItemBlock {

    public ItemStep(int i) {
        super(i);
        this.d(0);
        this.a(true);
    }

    public int filterData(int i) {
        return i;
    }

    public String a(ItemStack itemstack) {
        int i = itemstack.getData();

        if (i < 0 || i >= BlockStep.a.length) {
            i = 0;
        }

        return super.b() + "." + BlockStep.a[i];
    }
}

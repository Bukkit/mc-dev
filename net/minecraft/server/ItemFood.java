package net.minecraft.server;

public class ItemFood extends Item {

    private int a;
    private boolean bi;

    public ItemFood(int i, int j, boolean flag) {
        super(i);
        this.a = j;
        this.bi = flag;
        this.maxStackSize = 1;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        --itemstack.count;
        entityhuman.b(this.a);
        return itemstack;
    }

    public int j() {
        return this.a;
    }

    public boolean k() {
        return this.bi;
    }
}

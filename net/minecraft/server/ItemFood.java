package net.minecraft.server;

public class ItemFood extends Item {

    private int a;

    public ItemFood(int i, int j) {
        super(i);
        this.a = j;
        this.maxStackSize = 1;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        --itemstack.count;
        entityhuman.b(this.a);
        return itemstack;
    }
}

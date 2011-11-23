package net.minecraft.server;

public class ItemMilkBucket extends Item {

    public ItemMilkBucket(int i) {
        super(i);
        this.e(1);
    }

    public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman) {
        --itemstack.count;
        if (!world.isStatic) {
            entityhuman.ar();
        }

        return itemstack.count <= 0 ? new ItemStack(Item.BUCKET) : itemstack;
    }

    public int c(ItemStack itemstack) {
        return 32;
    }

    public EnumAnimation d(ItemStack itemstack) {
        return EnumAnimation.c;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        entityhuman.a(itemstack, this.c(itemstack));
        return itemstack;
    }
}

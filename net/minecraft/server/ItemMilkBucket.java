package net.minecraft.server;

public class ItemMilkBucket extends Item {

    public ItemMilkBucket(int i) {
        super(i);
        this.d(1);
        this.a(CreativeModeTab.f);
    }

    public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (!entityhuman.abilities.canInstantlyBuild) {
            --itemstack.count;
        }

        if (!world.isStatic) {
            entityhuman.bB();
        }

        return itemstack.count <= 0 ? new ItemStack(Item.BUCKET) : itemstack;
    }

    public int c_(ItemStack itemstack) {
        return 32;
    }

    public EnumAnimation b_(ItemStack itemstack) {
        return EnumAnimation.DRINK;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        entityhuman.a(itemstack, this.c_(itemstack));
        return itemstack;
    }
}

package net.minecraft.server;

public class ItemFishingRod extends Item {

    public ItemFishingRod(int i) {
        super(i);
        this.aY = 64;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (entityhuman.au != null) {
            int i = entityhuman.au.c();

            itemstack.a(i);
            entityhuman.F();
        } else {
            world.a(entityhuman, "random.bow", 0.5F, 0.4F / (b.nextFloat() * 0.4F + 0.8F));
            if (!world.z) {
                world.a((Entity) (new EntityFish(world, entityhuman)));
            }

            entityhuman.F();
        }

        return itemstack;
    }
}

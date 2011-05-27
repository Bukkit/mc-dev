package net.minecraft.server;

public class ItemFishingRod extends Item {

    public ItemFishingRod(int i) {
        super(i);
        this.durability = 64;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (entityhuman.hookedFish != null) {
            int i = entityhuman.hookedFish.d();

            itemstack.b(i);
            entityhuman.K();
        } else {
            world.a(entityhuman, "random.bow", 0.5F, 0.4F / (b.nextFloat() * 0.4F + 0.8F));
            if (!world.isStatic) {
                world.a((Entity) (new EntityFish(world, entityhuman)));
            }

            entityhuman.K();
        }

        return itemstack;
    }
}

package net.minecraft.server;

public class ItemFishingRod extends Item {

    public ItemFishingRod(int i) {
        super(i);
        this.setMaxDurability(64);
        this.e(1);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (entityhuman.hookedFish != null) {
            int i = entityhuman.hookedFish.j();

            itemstack.damage(i, entityhuman);
            entityhuman.s_();
        } else {
            world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (c.nextFloat() * 0.4F + 0.8F));
            if (!world.isStatic) {
                world.addEntity(new EntityFishingHook(world, entityhuman));
            }

            entityhuman.s_();
        }

        return itemstack;
    }
}

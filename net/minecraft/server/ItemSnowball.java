package net.minecraft.server;

public class ItemSnowball extends Item {

    public ItemSnowball(int i) {
        super(i);
        this.maxStackSize = 16;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        --itemstack.count;
        world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (b.nextFloat() * 0.4F + 0.8F));
        if (!world.isStatic) {
            world.addEntity(new EntitySnowball(world, entityhuman));
        }

        return itemstack;
    }
}

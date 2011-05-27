package net.minecraft.server;

public class ItemEgg extends Item {

    public ItemEgg(int i) {
        super(i);
        this.maxStackSize = 16;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        --itemstack.count;
        world.a(entityhuman, "random.bow", 0.5F, 0.4F / (b.nextFloat() * 0.4F + 0.8F));
        if (!world.isStatic) {
            world.a((Entity) (new EntityEgg(world, entityhuman)));
        }

        return itemstack;
    }
}

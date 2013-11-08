package net.minecraft.server;

public class ItemExpBottle extends Item {

    public ItemExpBottle() {
        this.a(CreativeModeTab.f);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (!entityhuman.abilities.canInstantlyBuild) {
            --itemstack.count;
        }

        world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (g.nextFloat() * 0.4F + 0.8F));
        if (!world.isStatic) {
            world.addEntity(new EntityThrownExpBottle(world, entityhuman));
        }

        return itemstack;
    }
}

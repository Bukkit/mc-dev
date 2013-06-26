package net.minecraft.server;

public class ItemEnderPearl extends Item {

    public ItemEnderPearl(int i) {
        super(i);
        this.maxStackSize = 16;
        this.a(CreativeModeTab.f);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (entityhuman.abilities.canInstantlyBuild) {
            return itemstack;
        } else {
            --itemstack.count;
            world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (f.nextFloat() * 0.4F + 0.8F));
            if (!world.isStatic) {
                world.addEntity(new EntityEnderPearl(world, entityhuman));
            }

            return itemstack;
        }
    }
}

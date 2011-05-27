package net.minecraft.server;

public class ItemBow extends Item {

    public ItemBow(int i) {
        super(i);
        this.maxStackSize = 1;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (entityhuman.inventory.b(Item.ARROW.id)) {
            world.makeSound(entityhuman, "random.bow", 1.0F, 1.0F / (b.nextFloat() * 0.4F + 0.8F));
            if (!world.isStatic) {
                world.addEntity(new EntityArrow(world, entityhuman));
            }
        }

        return itemstack;
    }
}

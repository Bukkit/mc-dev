package net.minecraft.server;

public class ItemBow extends Item {

    public ItemBow(int i) {
        super(i);
        this.bb = 1;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (entityhuman.an.b(Item.ARROW.ba)) {
            world.a(entityhuman, "random.bow", 1.0F, 1.0F / (b.nextFloat() * 0.4F + 0.8F));
            if (!world.z) {
                world.a((Entity) (new EntityArrow(world, entityhuman)));
            }
        }

        return itemstack;
    }
}

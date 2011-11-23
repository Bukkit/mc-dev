package net.minecraft.server;

public class ItemBow extends Item {

    public ItemBow(int i) {
        super(i);
        this.maxStackSize = 1;
        this.f(384);
    }

    public void a(ItemStack itemstack, World world, EntityHuman entityhuman, int i) {
        if (entityhuman.abilities.canInstantlyBuild || entityhuman.inventory.c(Item.ARROW.id)) {
            int j = this.c(itemstack) - i;
            float f = (float) j / 20.0F;

            f = (f * f + f * 2.0F) / 3.0F;
            if ((double) f < 0.1D) {
                return;
            }

            if (f > 1.0F) {
                f = 1.0F;
            }

            EntityArrow entityarrow = new EntityArrow(world, entityhuman, f * 2.0F);

            if (f == 1.0F) {
                entityarrow.d = true;
            }

            itemstack.damage(1, entityhuman);
            world.makeSound(entityhuman, "random.bow", 1.0F, 1.0F / (c.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            entityhuman.inventory.b(Item.ARROW.id);
            if (!world.isStatic) {
                world.addEntity(entityarrow);
            }
        }
    }

    public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman) {
        return itemstack;
    }

    public int c(ItemStack itemstack) {
        return 72000;
    }

    public EnumAnimation d(ItemStack itemstack) {
        return EnumAnimation.e;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (entityhuman.abilities.canInstantlyBuild || entityhuman.inventory.c(Item.ARROW.id)) {
            entityhuman.a(itemstack, this.c(itemstack));
        }

        return itemstack;
    }
}

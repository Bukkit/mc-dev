package net.minecraft.server;

public class ItemGlassBottle extends Item {

    public ItemGlassBottle() {
        this.a(CreativeModeTab.k);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        MovingObjectPosition movingobjectposition = this.a(world, entityhuman, true);

        if (movingobjectposition == null) {
            return itemstack;
        } else {
            if (movingobjectposition.type == EnumMovingObjectType.BLOCK) {
                int i = movingobjectposition.b;
                int j = movingobjectposition.c;
                int k = movingobjectposition.d;

                if (!world.a(entityhuman, i, j, k)) {
                    return itemstack;
                }

                if (!entityhuman.a(i, j, k, movingobjectposition.face, itemstack)) {
                    return itemstack;
                }

                if (world.getType(i, j, k).getMaterial() == Material.WATER) {
                    --itemstack.count;
                    if (itemstack.count <= 0) {
                        return new ItemStack(Items.POTION);
                    }

                    if (!entityhuman.inventory.pickup(new ItemStack(Items.POTION))) {
                        entityhuman.drop(new ItemStack(Items.POTION, 1, 0), false);
                    }
                }
            }

            return itemstack;
        }
    }
}

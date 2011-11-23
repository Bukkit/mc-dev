package net.minecraft.server;

public class ItemGlassBottle extends Item {

    public ItemGlassBottle(int i) {
        super(i);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        MovingObjectPosition movingobjectposition = this.a(world, entityhuman, true);

        if (movingobjectposition == null) {
            return itemstack;
        } else {
            if (movingobjectposition.type == EnumMovingObjectType.TILE) {
                int i = movingobjectposition.b;
                int j = movingobjectposition.c;
                int k = movingobjectposition.d;

                if (!world.a(entityhuman, i, j, k)) {
                    return itemstack;
                }

                if (!entityhuman.d(i, j, k)) {
                    return itemstack;
                }

                if (world.getMaterial(i, j, k) == Material.WATER) {
                    --itemstack.count;
                    if (itemstack.count <= 0) {
                        return new ItemStack(Item.POTION);
                    }

                    if (!entityhuman.inventory.pickup(new ItemStack(Item.POTION))) {
                        entityhuman.b(new ItemStack(Item.POTION.id, 1, 0));
                    }
                }
            }

            return itemstack;
        }
    }
}

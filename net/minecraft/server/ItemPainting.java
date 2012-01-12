package net.minecraft.server;

public class ItemPainting extends Item {

    public ItemPainting(int i) {
        super(i);
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (l == 0) {
            return false;
        } else if (l == 1) {
            return false;
        } else {
            byte b0 = 0;

            if (l == 4) {
                b0 = 1;
            }

            if (l == 3) {
                b0 = 2;
            }

            if (l == 5) {
                b0 = 3;
            }

            if (!entityhuman.d(i, j, k)) {
                return false;
            } else {
                EntityPainting entitypainting = new EntityPainting(world, i, j, k, b0);

                if (entitypainting.survives()) {
                    if (!world.isStatic) {
                        world.addEntity(entitypainting);
                    }

                    --itemstack.count;
                }

                return true;
            }
        }
    }
}

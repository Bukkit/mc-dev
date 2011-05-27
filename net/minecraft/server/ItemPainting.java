package net.minecraft.server;

public class ItemPainting extends Item {

    public ItemPainting(int i) {
        super(i);
        this.aY = 64;
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

            EntityPainting entitypainting = new EntityPainting(world, i, j, k, b0);

            if (entitypainting.c()) {
                world.a((Entity) entitypainting);
                --itemstack.a;
            }

            return true;
        }
    }
}

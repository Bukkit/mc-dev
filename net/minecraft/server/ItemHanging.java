package net.minecraft.server;

public class ItemHanging extends Item {

    private final Class a;

    public ItemHanging(Class oclass) {
        this.a = oclass;
        this.a(CreativeModeTab.c);
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        if (l == 0) {
            return false;
        } else if (l == 1) {
            return false;
        } else {
            int i1 = Direction.e[l];
            EntityHanging entityhanging = this.a(world, i, j, k, i1);

            if (!entityhuman.a(i, j, k, l, itemstack)) {
                return false;
            } else {
                if (entityhanging != null && entityhanging.survives()) {
                    if (!world.isStatic) {
                        world.addEntity(entityhanging);
                    }

                    --itemstack.count;
                }

                return true;
            }
        }
    }

    private EntityHanging a(World world, int i, int j, int k, int l) {
        return (EntityHanging) (this.a == EntityPainting.class ? new EntityPainting(world, i, j, k, l) : (this.a == EntityItemFrame.class ? new EntityItemFrame(world, i, j, k, l) : null));
    }
}

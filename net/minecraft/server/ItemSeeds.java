package net.minecraft.server;

public class ItemSeeds extends Item {

    private int a;

    public ItemSeeds(int i, int j) {
        super(i);
        this.a = j;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (l != 1) {
            return false;
        } else {
            int i1 = world.getTypeId(i, j, k);

            if (i1 == Block.SOIL.id && world.isEmpty(i, j + 1, k)) {
                world.e(i, j + 1, k, this.a);
                --itemstack.count;
                return true;
            } else {
                return false;
            }
        }
    }
}

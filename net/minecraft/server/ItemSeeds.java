package net.minecraft.server;

public class ItemSeeds extends Item {

    private int id;
    private int b;

    public ItemSeeds(int i, int j, int k) {
        super(i);
        this.id = j;
        this.b = k;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (l != 1) {
            return false;
        } else if (entityhuman.d(i, j, k) && entityhuman.d(i, j + 1, k)) {
            int i1 = world.getTypeId(i, j, k);

            if (i1 == this.b && world.isEmpty(i, j + 1, k)) {
                world.setTypeId(i, j + 1, k, this.id);
                --itemstack.count;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

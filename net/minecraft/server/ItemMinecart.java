package net.minecraft.server;

public class ItemMinecart extends Item {

    public int a;

    public ItemMinecart(int i, int j) {
        super(i);
        this.maxStackSize = 1;
        this.a = j;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        int i1 = world.getTypeId(i, j, k);

        if (BlockMinecartTrack.d(i1)) {
            if (!world.isStatic) {
                world.addEntity(new EntityMinecart(world, (double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), this.a));
            }

            --itemstack.count;
            return true;
        } else {
            return false;
        }
    }
}

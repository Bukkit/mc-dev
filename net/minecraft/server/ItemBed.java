package net.minecraft.server;

public class ItemBed extends Item {

    public ItemBed(int i) {
        super(i);
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (l != 1) {
            return false;
        } else {
            ++j;
            BlockBed blockbed = (BlockBed) Block.BED;
            int i1 = MathHelper.b((double) (entityhuman.yaw * 4.0F / 360.0F) + 0.5D) & 3;
            byte b0 = 0;
            byte b1 = 0;

            if (i1 == 0) {
                b1 = 1;
            }

            if (i1 == 1) {
                b0 = -1;
            }

            if (i1 == 2) {
                b1 = -1;
            }

            if (i1 == 3) {
                b0 = 1;
            }

            if (world.isEmpty(i, j, k) && world.isEmpty(i + b0, j, k + b1) && world.d(i, j - 1, k) && world.d(i + b0, j - 1, k + b1)) {
                world.b(i, j, k, blockbed.id, i1);
                world.b(i + b0, j, k + b1, blockbed.id, i1 + 8);
                --itemstack.count;
                return true;
            } else {
                return false;
            }
        }
    }
}

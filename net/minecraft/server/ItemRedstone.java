package net.minecraft.server;

public class ItemRedstone extends Item {

    public ItemRedstone(int i) {
        super(i);
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        if (l == 0) {
            --j;
        }

        if (l == 1) {
            ++j;
        }

        if (l == 2) {
            --k;
        }

        if (l == 3) {
            ++k;
        }

        if (l == 4) {
            --i;
        }

        if (l == 5) {
            ++i;
        }

        if (world.a(i, j, k) != 0) {
            return false;
        } else {
            if (Block.REDSTONE_WIRE.a(world, i, j, k)) {
                --itemstack.a;
                world.d(i, j, k, Block.REDSTONE_WIRE.bi);
            }

            return true;
        }
    }
}

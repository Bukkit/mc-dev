package net.minecraft.server;

public class ItemFlintAndSteel extends Item {

    public ItemFlintAndSteel(int i) {
        super(i);
        this.aT = 1;
        this.aU = 64;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
        int dbl = world.a(i, j, k);

        if (dbl == 52 || dbl == 46) {
            world.d(i, j, k, 0);
        }

        if (dbl == 7 && j > 5) {
            world.d(i, j, k, 0);
        }

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

        int i = world.a(i, j, k);

        if (i == 0) {
            world.d(i, j, k, 20);
            world.d(i, j, k, 0);
        }

        itemstack.a(1);
        return true;
    }
}

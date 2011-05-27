package net.minecraft.server;

public class ItemBucket extends Item {

    private int a;

    public ItemBucket(int i, int j) {
        super(i);
        this.aT = 1;
        this.aU = 64;
        this.a = j;
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

        int i;

        if (this.a == 0) {
            i = world.a(i, j, k);
            if (i == 8 || i == 9 || i == 10 || i == 11) {
                world.d(i, j, k, 0);
            }
        }

        i = world.a(i, j, k);
        if (i == 0) {
            world.d(i, j, k, this.a);
        }

        itemstack.a(1);
        return true;
    }
}

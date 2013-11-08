package net.minecraft.server;

public class ItemSoup extends ItemFood {

    public ItemSoup(int i) {
        super(i, false);
        this.e(1);
    }

    public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman) {
        super.b(itemstack, world, entityhuman);
        return new ItemStack(Items.BOWL);
    }
}

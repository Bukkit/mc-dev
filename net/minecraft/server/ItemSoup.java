package net.minecraft.server;

public class ItemSoup extends ItemFood {

    public ItemSoup(int i, int j) {
        super(i, j, false);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        super.a(itemstack, world, entityhuman);
        return new ItemStack(Item.BOWL);
    }
}

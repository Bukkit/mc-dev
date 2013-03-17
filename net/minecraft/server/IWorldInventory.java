package net.minecraft.server;

public interface IWorldInventory extends IInventory {

    int[] getSlotsForFace(int i);

    boolean canPlaceItemThroughFace(int i, ItemStack itemstack, int j);

    boolean canTakeItemThroughFace(int i, ItemStack itemstack, int j);
}

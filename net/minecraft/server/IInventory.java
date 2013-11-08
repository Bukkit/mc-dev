package net.minecraft.server;

public interface IInventory {

    int getSize();

    ItemStack getItem(int i);

    ItemStack splitStack(int i, int j);

    ItemStack splitWithoutUpdate(int i);

    void setItem(int i, ItemStack itemstack);

    String getInventoryName();

    boolean k_();

    int getMaxStackSize();

    void update();

    boolean a(EntityHuman entityhuman);

    void startOpen();

    void l_();

    boolean b(int i, ItemStack itemstack);
}

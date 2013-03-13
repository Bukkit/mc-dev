package net.minecraft.server;

public interface IInventory {

    int getSize();

    ItemStack getItem(int i);

    ItemStack splitStack(int i, int j);

    ItemStack splitWithoutUpdate(int i);

    void setItem(int i, ItemStack itemstack);

    String getName();

    boolean c();

    int getMaxStackSize();

    void update();

    boolean a(EntityHuman entityhuman);

    void startOpen();

    void g();

    boolean b(int i, ItemStack itemstack);
}

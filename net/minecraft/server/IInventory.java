package net.minecraft.server;

public interface IInventory {

    int getSize();

    ItemStack getItem(int i);

    ItemStack splitStack(int i, int j);

    ItemStack splitWithoutUpdate(int i);

    void setItem(int i, ItemStack itemstack);

    String getName();

    int getMaxStackSize();

    void update();

    boolean a(EntityHuman entityhuman);

    void f();

    void g();
}

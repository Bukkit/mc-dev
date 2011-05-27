package net.minecraft.server;

public interface IInventory {

    int a();

    ItemStack a(int i);

    ItemStack a(int i, int j);

    void a(int i, ItemStack itemstack);

    String b();

    int c();

    void d();

    boolean a_(EntityHuman entityhuman);
}

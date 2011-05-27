package net.minecraft.server;

public class InventoryCrafting implements IInventory {

    private ItemStack[] a;
    private int b;
    private Container c;

    public InventoryCrafting(Container container, int i, int j) {
        this.b = i * j;
        this.a = new ItemStack[this.b];
        this.c = container;
    }

    public int a() {
        return this.b;
    }

    public ItemStack a(int i) {
        return this.a[i];
    }

    public String b() {
        return "Crafting";
    }

    public ItemStack a(int i, int j) {
        if (this.a[i] != null) {
            ItemStack itemstack;

            if (this.a[i].a <= j) {
                itemstack = this.a[i];
                this.a[i] = null;
                this.c.a((IInventory) this);
                return itemstack;
            } else {
                itemstack = this.a[i].a(j);
                if (this.a[i].a == 0) {
                    this.a[i] = null;
                }

                this.c.a((IInventory) this);
                return itemstack;
            }
        } else {
            return null;
        }
    }

    public void a(int i, ItemStack itemstack) {
        this.a[i] = itemstack;
        this.c.a((IInventory) this);
    }

    public int c() {
        return 64;
    }

    public void d() {}

    public boolean a_(EntityHuman entityhuman) {
        return true;
    }
}

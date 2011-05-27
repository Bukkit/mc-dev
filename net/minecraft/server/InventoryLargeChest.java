package net.minecraft.server;

public class InventoryLargeChest implements IInventory {

    private String a;
    private IInventory b;
    private IInventory c;

    public InventoryLargeChest(String s, IInventory iinventory, IInventory iinventory1) {
        this.a = s;
        this.b = iinventory;
        this.c = iinventory1;
    }

    public int a() {
        return this.b.a() + this.c.a();
    }

    public ItemStack a(int i) {
        return i >= this.b.a() ? this.c.a(i - this.b.a()) : this.b.a(i);
    }
}

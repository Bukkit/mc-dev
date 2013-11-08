package net.minecraft.server;

public class InventoryLargeChest implements IInventory {

    private String a;
    private IInventory left;
    private IInventory right;

    public InventoryLargeChest(String s, IInventory iinventory, IInventory iinventory1) {
        this.a = s;
        if (iinventory == null) {
            iinventory = iinventory1;
        }

        if (iinventory1 == null) {
            iinventory1 = iinventory;
        }

        this.left = iinventory;
        this.right = iinventory1;
    }

    public int getSize() {
        return this.left.getSize() + this.right.getSize();
    }

    public boolean a(IInventory iinventory) {
        return this.left == iinventory || this.right == iinventory;
    }

    public String getInventoryName() {
        return this.left.k_() ? this.left.getInventoryName() : (this.right.k_() ? this.right.getInventoryName() : this.a);
    }

    public boolean k_() {
        return this.left.k_() || this.right.k_();
    }

    public ItemStack getItem(int i) {
        return i >= this.left.getSize() ? this.right.getItem(i - this.left.getSize()) : this.left.getItem(i);
    }

    public ItemStack splitStack(int i, int j) {
        return i >= this.left.getSize() ? this.right.splitStack(i - this.left.getSize(), j) : this.left.splitStack(i, j);
    }

    public ItemStack splitWithoutUpdate(int i) {
        return i >= this.left.getSize() ? this.right.splitWithoutUpdate(i - this.left.getSize()) : this.left.splitWithoutUpdate(i);
    }

    public void setItem(int i, ItemStack itemstack) {
        if (i >= this.left.getSize()) {
            this.right.setItem(i - this.left.getSize(), itemstack);
        } else {
            this.left.setItem(i, itemstack);
        }
    }

    public int getMaxStackSize() {
        return this.left.getMaxStackSize();
    }

    public void update() {
        this.left.update();
        this.right.update();
    }

    public boolean a(EntityHuman entityhuman) {
        return this.left.a(entityhuman) && this.right.a(entityhuman);
    }

    public void startOpen() {
        this.left.startOpen();
        this.right.startOpen();
    }

    public void l_() {
        this.left.l_();
        this.right.l_();
    }

    public boolean b(int i, ItemStack itemstack) {
        return true;
    }
}

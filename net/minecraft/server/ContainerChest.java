package net.minecraft.server;

public class ContainerChest extends Container {

    private IInventory a;
    private int b;

    public ContainerChest(IInventory iinventory, IInventory iinventory1) {
        this.a = iinventory1;
        this.b = iinventory1.getSize() / 9;
        int i = (this.b - 4) * 18;

        int j;
        int k;

        for (j = 0; j < this.b; ++j) {
            for (k = 0; k < 9; ++k) {
                this.a(new Slot(iinventory1, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (j = 0; j < 3; ++j) {
            for (k = 0; k < 9; ++k) {
                this.a(new Slot(iinventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }

        for (j = 0; j < 9; ++j) {
            this.a(new Slot(iinventory, j, 8 + j * 18, 161 + i));
        }
    }

    public boolean b(EntityHuman entityhuman) {
        return this.a.a_(entityhuman);
    }

    public ItemStack a(int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.e.get(i);

        if (slot != null && slot.b()) {
            ItemStack itemstack1 = slot.getItem();

            itemstack = itemstack1.cloneItemStack();
            if (i < this.b * 9) {
                this.a(itemstack1, this.b * 9, this.e.size(), true);
            } else {
                this.a(itemstack1, 0, this.b * 9, false);
            }

            if (itemstack1.count == 0) {
                slot.c((ItemStack) null);
            } else {
                slot.c();
            }
        }

        return itemstack;
    }
}

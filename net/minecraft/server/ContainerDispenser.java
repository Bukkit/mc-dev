package net.minecraft.server;

public class ContainerDispenser extends Container {

    private TileEntityDispenser items;

    public ContainerDispenser(IInventory iinventory, TileEntityDispenser tileentitydispenser) {
        this.items = tileentitydispenser;

        int i;
        int j;

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 3; ++j) {
                this.a(new Slot(tileentitydispenser, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.a(new Slot(iinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.a(new Slot(iinventory, i, 8 + i * 18, 142));
        }
    }

    public boolean c(EntityHuman entityhuman) {
        return this.items.a(entityhuman);
    }

    public ItemStack b(EntityHuman entityhuman, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.b.get(i);

        if (slot != null && slot.d()) {
            ItemStack itemstack1 = slot.getItem();

            itemstack = itemstack1.cloneItemStack();
            if (i < 9) {
                if (!this.a(itemstack1, 9, 45, true)) {
                    return null;
                }
            } else if (!this.a(itemstack1, 0, 9, false)) {
                return null;
            }

            if (itemstack1.count == 0) {
                slot.set((ItemStack) null);
            } else {
                slot.e();
            }

            if (itemstack1.count == itemstack.count) {
                return null;
            }

            slot.a(entityhuman, itemstack1);
        }

        return itemstack;
    }
}

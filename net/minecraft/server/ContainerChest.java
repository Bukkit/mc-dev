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

    private void a(ItemStack itemstack, int i, int j) {
        int k = i;
        Slot slot;
        ItemStack itemstack1;

        if (itemstack.c()) {
            for (; itemstack.count > 0 && k < j; ++k) {
                slot = (Slot) this.e.get(k);
                itemstack1 = slot.getItem();
                if (itemstack1 != null && itemstack1.id == itemstack.id && (!itemstack.e() || itemstack.getData() == itemstack1.getData())) {
                    int l = itemstack1.count + itemstack.count;

                    if (l <= itemstack.b()) {
                        itemstack.count = 0;
                        itemstack1.count = l;
                        slot.c();
                    } else if (itemstack1.count < itemstack.b()) {
                        itemstack.count -= itemstack.b() - itemstack1.count;
                        itemstack1.count = itemstack.b();
                        slot.c();
                    }
                }
            }
        }

        if (itemstack.count > 0) {
            for (k = i; k < j; ++k) {
                slot = (Slot) this.e.get(k);
                itemstack1 = slot.getItem();
                if (itemstack1 == null) {
                    slot.c(itemstack.j());
                    slot.c();
                    itemstack.count = 0;
                    break;
                }
            }
        }
    }

    public ItemStack a(int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.e.get(i);

        if (slot != null && slot.b()) {
            ItemStack itemstack1 = slot.getItem();

            itemstack = itemstack1.j();
            if (i < this.b * 9) {
                this.a(itemstack1, this.b * 9, this.e.size());
            } else {
                this.a(itemstack1, 0, this.b * 9);
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

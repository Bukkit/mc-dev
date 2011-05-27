package net.minecraft.server;

public class SlotResult extends Slot {

    private final IInventory a;

    public SlotResult(IInventory iinventory, IInventory iinventory1, int i, int j, int k) {
        super(iinventory1, i, j, k);
        this.a = iinventory;
    }

    public boolean a(ItemStack itemstack) {
        return false;
    }

    public void b() {
        for (int i = 0; i < this.a.h_(); ++i) {
            ItemStack itemstack = this.a.a(i);

            if (itemstack != null) {
                this.a.b(i, 1);
                if (itemstack.a().g()) {
                    this.a.a(i, new ItemStack(itemstack.a().f()));
                }
            }
        }
    }
}

package net.minecraft.server;

public class SlotResult extends Slot {

    private final IInventory d;

    public SlotResult(IInventory iinventory, IInventory iinventory1, int i, int j, int k) {
        super(iinventory1, i, j, k);
        this.d = iinventory;
    }

    public boolean a(ItemStack itemstack) {
        return false;
    }

    public void a() {
        for (int i = 0; i < this.d.m_(); ++i) {
            ItemStack itemstack = this.d.c_(i);

            if (itemstack != null) {
                this.d.a(i, 1);
                if (itemstack.a().g()) {
                    this.d.a(i, new ItemStack(itemstack.a().f()));
                }
            }
        }
    }
}

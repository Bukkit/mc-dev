package net.minecraft.server;

class SlotEnchant extends Slot {

    final ContainerEnchantTable a;

    SlotEnchant(ContainerEnchantTable containerenchanttable, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.a = containerenchanttable;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return true;
    }
}

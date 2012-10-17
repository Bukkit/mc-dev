package net.minecraft.server;

class SlotBeacon extends Slot {

    final ContainerBeacon a;

    public SlotBeacon(ContainerBeacon containerbeacon, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.a = containerbeacon;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return itemstack == null ? false : itemstack.id == Item.EMERALD.id || itemstack.id == Item.DIAMOND.id || itemstack.id == Item.GOLD_INGOT.id || itemstack.id == Item.IRON_INGOT.id;
    }

    public int a() {
        return 1;
    }
}

package net.minecraft.server;

class SlotHorseSaddle extends Slot {

    final ContainerHorse a;

    SlotHorseSaddle(ContainerHorse containerhorse, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.a = containerhorse;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return super.isAllowed(itemstack) && itemstack.getItem() == Items.SADDLE && !this.hasItem();
    }
}

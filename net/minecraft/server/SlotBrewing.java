package net.minecraft.server;

class SlotBrewing extends Slot {

    final ContainerBrewingStand a;

    public SlotBrewing(ContainerBrewingStand containerbrewingstand, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.a = containerbrewingstand;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return itemstack != null ? Item.byId[itemstack.id].m() : false;
    }

    public int a() {
        return 64;
    }
}

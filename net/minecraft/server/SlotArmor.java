package net.minecraft.server;

class SlotArmor extends Slot {

    final int a;
    final ContainerPlayer b;

    SlotArmor(ContainerPlayer containerplayer, IInventory iinventory, int i, int j, int k, int l) {
        super(iinventory, i, j, k);
        this.b = containerplayer;
        this.a = l;
    }

    public int getMaxStackSize() {
        return 1;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return itemstack == null ? false : (itemstack.getItem() instanceof ItemArmor ? ((ItemArmor) itemstack.getItem()).b == this.a : (itemstack.getItem() != Item.getItemOf(Blocks.PUMPKIN) && itemstack.getItem() != Items.SKULL ? false : this.a == 0));
    }
}

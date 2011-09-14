package net.minecraft.server;

class SlotArmor extends Slot {

    final int e;

    final ContainerPlayer f;

    SlotArmor(ContainerPlayer containerplayer, IInventory iinventory, int i, int j, int k, int l) {
        super(iinventory, i, j, k);
        this.f = containerplayer;
        this.e = l;
    }

    public int d() {
        return 1;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return itemstack.getItem() instanceof ItemArmor ? ((ItemArmor) itemstack.getItem()).bt == this.e : (itemstack.getItem().id == Block.PUMPKIN.id ? this.e == 0 : false);
    }
}

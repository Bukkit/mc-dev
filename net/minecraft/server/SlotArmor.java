package net.minecraft.server;

class SlotArmor extends Slot {

    final int a;

    final ContainerPlayer f;

    SlotArmor(ContainerPlayer containerplayer, IInventory iinventory, int i, int j, int k, int l) {
        super(iinventory, i, j, k);
        this.f = containerplayer;
        this.a = l;
    }

    public int a() {
        return 1;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return itemstack.getItem() instanceof ItemArmor ? ((ItemArmor) itemstack.getItem()).a == this.a : (itemstack.getItem().id == Block.PUMPKIN.id ? this.a == 0 : false);
    }
}

package net.minecraft.server;

class SlotArmor extends Slot {

    final int a;

    final ContainerPlayer b;

    SlotArmor(ContainerPlayer containerplayer, IInventory iinventory, int i, int j, int k, int l) {
        super(iinventory, i, j, k);
        this.b = containerplayer;
        this.a = l;
    }

    public int a() {
        return 1;
    }

    public boolean a(ItemStack itemstack) {
        return itemstack.a() instanceof ItemArmor ? ((ItemArmor) itemstack.a()).bb == this.a : (itemstack.a().aW == Block.PUMPKIN.bh ? this.a == 0 : false);
    }
}

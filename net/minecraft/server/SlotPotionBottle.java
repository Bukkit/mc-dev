package net.minecraft.server;

class SlotPotionBottle extends Slot {

    private EntityHuman a;

    public SlotPotionBottle(EntityHuman entityhuman, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.a = entityhuman;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return b_(itemstack);
    }

    public int a() {
        return 1;
    }

    public void a(EntityHuman entityhuman, ItemStack itemstack) {
        if (itemstack.id == Item.POTION.id && itemstack.getData() > 0) {
            this.a.a((Statistic) AchievementList.A, 1);
        }

        super.a(entityhuman, itemstack);
    }

    public static boolean b_(ItemStack itemstack) {
        return itemstack != null && (itemstack.id == Item.POTION.id || itemstack.id == Item.GLASS_BOTTLE.id);
    }
}

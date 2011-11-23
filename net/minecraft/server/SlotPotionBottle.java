package net.minecraft.server;

class SlotPotionBottle extends Slot {

    private EntityHuman f;

    final ContainerBrewingStand a;

    public SlotPotionBottle(ContainerBrewingStand containerbrewingstand, EntityHuman entityhuman, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.a = containerbrewingstand;
        this.f = entityhuman;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return itemstack != null && (itemstack.id == Item.POTION.id || itemstack.id == Item.GLASS_BOTTLE.id);
    }

    public int a() {
        return 1;
    }

    public void b(ItemStack itemstack) {
        if (itemstack.id == Item.POTION.id && itemstack.getData() > 0) {
            this.f.a((Statistic) AchievementList.A, 1);
        }

        super.b(itemstack);
    }
}

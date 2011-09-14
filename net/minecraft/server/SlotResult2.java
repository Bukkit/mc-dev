package net.minecraft.server;

public class SlotResult2 extends Slot {

    private EntityHuman e;

    public SlotResult2(EntityHuman entityhuman, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.e = entityhuman;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return false;
    }

    public void a(ItemStack itemstack) {
        itemstack.c(this.e.world, this.e);
        if (itemstack.id == Item.IRON_INGOT.id) {
            this.e.a((Statistic) AchievementList.k, 1);
        }

        if (itemstack.id == Item.COOKED_FISH.id) {
            this.e.a((Statistic) AchievementList.p, 1);
        }

        super.a(itemstack);
    }
}

package net.minecraft.server;

public class SlotResult2 extends Slot {

    private EntityHuman a;

    public SlotResult2(EntityHuman entityhuman, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.a = entityhuman;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return false;
    }

    public void b(ItemStack itemstack) {
        itemstack.c(this.a.world, this.a);
        if (itemstack.id == Item.IRON_INGOT.id) {
            this.a.a((Statistic) AchievementList.k, 1);
        }

        if (itemstack.id == Item.COOKED_FISH.id) {
            this.a.a((Statistic) AchievementList.p, 1);
        }

        super.b(itemstack);
    }
}

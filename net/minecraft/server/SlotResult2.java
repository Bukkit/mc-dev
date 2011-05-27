package net.minecraft.server;

public class SlotResult2 extends Slot {

    private EntityHuman d;

    public SlotResult2(EntityHuman entityhuman, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.d = entityhuman;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return false;
    }

    public boolean e() {
        return true;
    }

    public void a(ItemStack itemstack) {
        if (itemstack.id == Item.IRON_INGOT.id) {
            this.d.a(AchievementList.k, 1);
        }

        if (itemstack.id == Item.COOKED_FISH.id) {
            this.d.a(AchievementList.p, 1);
        }

        super.a(itemstack);
    }
}

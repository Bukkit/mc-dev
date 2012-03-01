package net.minecraft.server;

public class SlotResult2 extends Slot {

    private EntityHuman a;
    private int f;

    public SlotResult2(EntityHuman entityhuman, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
        this.a = entityhuman;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return false;
    }

    public ItemStack a(int i) {
        if (this.c()) {
            this.f += Math.min(i, this.getItem().count);
        }

        return super.a(i);
    }

    public void c(ItemStack itemstack) {
        this.b(itemstack);
        super.c(itemstack);
    }

    protected void a(ItemStack itemstack, int i) {
        this.f += i;
        this.b(itemstack);
    }

    protected void b(ItemStack itemstack) {
        itemstack.a(this.a.world, this.a, this.f);
        this.f = 0;
        if (itemstack.id == Item.IRON_INGOT.id) {
            this.a.a((Statistic) AchievementList.k, 1);
        }

        if (itemstack.id == Item.COOKED_FISH.id) {
            this.a.a((Statistic) AchievementList.p, 1);
        }
    }
}

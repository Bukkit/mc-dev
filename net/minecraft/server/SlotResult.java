package net.minecraft.server;

public class SlotResult extends Slot {

    private final IInventory e;
    private EntityHuman f;

    public SlotResult(EntityHuman entityhuman, IInventory iinventory, IInventory iinventory1, int i, int j, int k) {
        super(iinventory1, i, j, k);
        this.f = entityhuman;
        this.e = iinventory;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return false;
    }

    public void a(ItemStack itemstack) {
        itemstack.c(this.f.world, this.f);
        if (itemstack.id == Block.WORKBENCH.id) {
            this.f.a((Statistic) AchievementList.h, 1);
        } else if (itemstack.id == Item.WOOD_PICKAXE.id) {
            this.f.a((Statistic) AchievementList.i, 1);
        } else if (itemstack.id == Block.FURNACE.id) {
            this.f.a((Statistic) AchievementList.j, 1);
        } else if (itemstack.id == Item.WOOD_HOE.id) {
            this.f.a((Statistic) AchievementList.l, 1);
        } else if (itemstack.id == Item.BREAD.id) {
            this.f.a((Statistic) AchievementList.m, 1);
        } else if (itemstack.id == Item.CAKE.id) {
            this.f.a((Statistic) AchievementList.n, 1);
        } else if (itemstack.id == Item.STONE_PICKAXE.id) {
            this.f.a((Statistic) AchievementList.o, 1);
        } else if (itemstack.id == Item.WOOD_SWORD.id) {
            this.f.a((Statistic) AchievementList.r, 1);
        }

        for (int i = 0; i < this.e.getSize(); ++i) {
            ItemStack itemstack1 = this.e.getItem(i);

            if (itemstack1 != null) {
                this.e.splitStack(i, 1);
                if (itemstack1.getItem().i()) {
                    this.e.setItem(i, new ItemStack(itemstack1.getItem().h()));
                }
            }
        }
    }
}

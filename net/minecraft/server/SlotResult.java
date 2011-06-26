package net.minecraft.server;

public class SlotResult extends Slot {

    private final IInventory d;
    private EntityHuman e;

    public SlotResult(EntityHuman entityhuman, IInventory iinventory, IInventory iinventory1, int i, int j, int k) {
        super(iinventory1, i, j, k);
        this.e = entityhuman;
        this.d = iinventory;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return false;
    }

    public void a(ItemStack itemstack) {
        itemstack.b(this.e.world, this.e);
        if (itemstack.id == Block.WORKBENCH.id) {
            this.e.a(AchievementList.h, 1);
        } else if (itemstack.id == Item.WOOD_PICKAXE.id) {
            this.e.a(AchievementList.i, 1);
        } else if (itemstack.id == Block.FURNACE.id) {
            this.e.a(AchievementList.j, 1);
        } else if (itemstack.id == Item.WOOD_HOE.id) {
            this.e.a(AchievementList.l, 1);
        } else if (itemstack.id == Item.BREAD.id) {
            this.e.a(AchievementList.m, 1);
        } else if (itemstack.id == Item.CAKE.id) {
            this.e.a(AchievementList.n, 1);
        } else if (itemstack.id == Item.STONE_PICKAXE.id) {
            this.e.a(AchievementList.o, 1);
        } else if (itemstack.id == Item.WOOD_SWORD.id) {
            this.e.a(AchievementList.r, 1);
        }

        for (int i = 0; i < this.d.getSize(); ++i) {
            ItemStack itemstack1 = this.d.getItem(i);

            if (itemstack1 != null) {
                this.d.splitStack(i, 1);
                if (itemstack1.getItem().i()) {
                    this.d.setItem(i, new ItemStack(itemstack1.getItem().h()));
                }
            }
        }
    }
}

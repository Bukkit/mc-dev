package net.minecraft.server;

public class SlotResult extends Slot {

    private final IInventory a;
    private EntityHuman f;
    private int g;

    public SlotResult(EntityHuman entityhuman, IInventory iinventory, IInventory iinventory1, int i, int j, int k) {
        super(iinventory1, i, j, k);
        this.f = entityhuman;
        this.a = iinventory;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return false;
    }

    public ItemStack a(int i) {
        if (this.c()) {
            this.g += Math.min(i, this.getItem().count);
        }

        return super.a(i);
    }

    protected void a(ItemStack itemstack, int i) {
        this.g += i;
        this.b(itemstack);
    }

    protected void b(ItemStack itemstack) {
        itemstack.a(this.f.world, this.f, this.g);
        this.g = 0;
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
        } else if (itemstack.id == Block.ENCHANTMENT_TABLE.id) {
            this.f.a((Statistic) AchievementList.D, 1);
        } else if (itemstack.id == Block.BOOKSHELF.id) {
            this.f.a((Statistic) AchievementList.F, 1);
        }
    }

    public void c(ItemStack itemstack) {
        this.b(itemstack);

        for (int i = 0; i < this.a.getSize(); ++i) {
            ItemStack itemstack1 = this.a.getItem(i);

            if (itemstack1 != null) {
                this.a.splitStack(i, 1);
                if (itemstack1.getItem().k()) {
                    ItemStack itemstack2 = new ItemStack(itemstack1.getItem().j());

                    if (!itemstack1.getItem().e(itemstack1) || !this.f.inventory.pickup(itemstack2)) {
                        if (this.a.getItem(i) == null) {
                            this.a.setItem(i, itemstack2);
                        } else {
                            this.f.drop(itemstack2);
                        }
                    }
                }
            }
        }
    }
}

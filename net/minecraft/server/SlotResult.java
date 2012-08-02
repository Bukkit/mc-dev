package net.minecraft.server;

public class SlotResult extends Slot {

    private final IInventory a;
    private EntityHuman b;
    private int g;

    public SlotResult(EntityHuman entityhuman, IInventory iinventory, IInventory iinventory1, int i, int j, int k) {
        super(iinventory1, i, j, k);
        this.b = entityhuman;
        this.a = iinventory;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return false;
    }

    public ItemStack a(int i) {
        if (this.d()) {
            this.g += Math.min(i, this.getItem().count);
        }

        return super.a(i);
    }

    protected void a(ItemStack itemstack, int i) {
        this.g += i;
        this.c(itemstack);
    }

    protected void c(ItemStack itemstack) {
        itemstack.a(this.b.world, this.b, this.g);
        this.g = 0;
        if (itemstack.id == Block.WORKBENCH.id) {
            this.b.a((Statistic) AchievementList.h, 1);
        } else if (itemstack.id == Item.WOOD_PICKAXE.id) {
            this.b.a((Statistic) AchievementList.i, 1);
        } else if (itemstack.id == Block.FURNACE.id) {
            this.b.a((Statistic) AchievementList.j, 1);
        } else if (itemstack.id == Item.WOOD_HOE.id) {
            this.b.a((Statistic) AchievementList.l, 1);
        } else if (itemstack.id == Item.BREAD.id) {
            this.b.a((Statistic) AchievementList.m, 1);
        } else if (itemstack.id == Item.CAKE.id) {
            this.b.a((Statistic) AchievementList.n, 1);
        } else if (itemstack.id == Item.STONE_PICKAXE.id) {
            this.b.a((Statistic) AchievementList.o, 1);
        } else if (itemstack.id == Item.WOOD_SWORD.id) {
            this.b.a((Statistic) AchievementList.r, 1);
        } else if (itemstack.id == Block.ENCHANTMENT_TABLE.id) {
            this.b.a((Statistic) AchievementList.D, 1);
        } else if (itemstack.id == Block.BOOKSHELF.id) {
            this.b.a((Statistic) AchievementList.F, 1);
        }
    }

    public void b(ItemStack itemstack) {
        this.c(itemstack);

        for (int i = 0; i < this.a.getSize(); ++i) {
            ItemStack itemstack1 = this.a.getItem(i);

            if (itemstack1 != null) {
                this.a.splitStack(i, 1);
                if (itemstack1.getItem().r()) {
                    ItemStack itemstack2 = new ItemStack(itemstack1.getItem().q());

                    if (!itemstack1.getItem().h(itemstack1) || !this.b.inventory.pickup(itemstack2)) {
                        if (this.a.getItem(i) == null) {
                            this.a.setItem(i, itemstack2);
                        } else {
                            this.b.drop(itemstack2);
                        }
                    }
                }
            }
        }
    }
}

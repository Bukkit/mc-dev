package net.minecraft.server;

public class SlotResult extends Slot {

    private final IInventory a;
    private EntityHuman b;
    private int c;

    public SlotResult(EntityHuman entityhuman, IInventory iinventory, IInventory iinventory1, int i, int j, int k) {
        super(iinventory1, i, j, k);
        this.b = entityhuman;
        this.a = iinventory;
    }

    public boolean isAllowed(ItemStack itemstack) {
        return false;
    }

    public ItemStack a(int i) {
        if (this.e()) {
            this.c += Math.min(i, this.getItem().count);
        }

        return super.a(i);
    }

    protected void a(ItemStack itemstack, int i) {
        this.c += i;
        this.b(itemstack);
    }

    protected void b(ItemStack itemstack) {
        itemstack.a(this.b.world, this.b, this.c);
        this.c = 0;
        if (itemstack.getItem() == Item.getItemOf(Blocks.WORKBENCH)) {
            this.b.a((Statistic) AchievementList.h, 1);
        }

        if (itemstack.getItem() instanceof ItemPickaxe) {
            this.b.a((Statistic) AchievementList.i, 1);
        }

        if (itemstack.getItem() == Item.getItemOf(Blocks.FURNACE)) {
            this.b.a((Statistic) AchievementList.j, 1);
        }

        if (itemstack.getItem() instanceof ItemHoe) {
            this.b.a((Statistic) AchievementList.l, 1);
        }

        if (itemstack.getItem() == Items.BREAD) {
            this.b.a((Statistic) AchievementList.m, 1);
        }

        if (itemstack.getItem() == Items.CAKE) {
            this.b.a((Statistic) AchievementList.n, 1);
        }

        if (itemstack.getItem() instanceof ItemPickaxe && ((ItemPickaxe) itemstack.getItem()).i() != EnumToolMaterial.WOOD) {
            this.b.a((Statistic) AchievementList.o, 1);
        }

        if (itemstack.getItem() instanceof ItemSword) {
            this.b.a((Statistic) AchievementList.r, 1);
        }

        if (itemstack.getItem() == Item.getItemOf(Blocks.ENCHANTMENT_TABLE)) {
            this.b.a((Statistic) AchievementList.E, 1);
        }

        if (itemstack.getItem() == Item.getItemOf(Blocks.BOOKSHELF)) {
            this.b.a((Statistic) AchievementList.G, 1);
        }
    }

    public void a(EntityHuman entityhuman, ItemStack itemstack) {
        this.b(itemstack);

        for (int i = 0; i < this.a.getSize(); ++i) {
            ItemStack itemstack1 = this.a.getItem(i);

            if (itemstack1 != null) {
                this.a.splitStack(i, 1);
                if (itemstack1.getItem().u()) {
                    ItemStack itemstack2 = new ItemStack(itemstack1.getItem().t());

                    if (!itemstack1.getItem().l(itemstack1) || !this.b.inventory.pickup(itemstack2)) {
                        if (this.a.getItem(i) == null) {
                            this.a.setItem(i, itemstack2);
                        } else {
                            this.b.drop(itemstack2, false);
                        }
                    }
                }
            }
        }
    }
}

package net.minecraft.server;

public class ContainerPlayer extends Container {

    public InventoryCrafting a;
    public IInventory b;
    public boolean c;

    public ContainerPlayer(InventoryPlayer inventoryplayer) {
        this(inventoryplayer, true);
    }

    public ContainerPlayer(InventoryPlayer inventoryplayer, boolean flag) {
        this.a = new InventoryCrafting(this, 2, 2);
        this.b = new InventoryCraftResult();
        this.c = false;
        this.c = flag;
        this.a((Slot) (new SlotResult(this.a, this.b, 0, 144, 36)));

        int i;
        int j;

        for (i = 0; i < 2; ++i) {
            for (j = 0; j < 2; ++j) {
                this.a(new Slot(this.a, j + i * 2, 88 + j * 18, 26 + i * 18));
            }
        }

        for (i = 0; i < 4; ++i) {
            this.a((Slot) (new SlotArmor(this, inventoryplayer, inventoryplayer.m_() - 1 - i, 8, 8 + i * 18, i)));
        }

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.a(new Slot(inventoryplayer, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.a(new Slot(inventoryplayer, i, 8 + i * 18, 142));
        }

        this.a((IInventory) this.a);
    }

    public void a(IInventory iinventory) {
        this.b.a(0, CraftingManager.a().a(this.a));
    }

    public void a(EntityHuman entityhuman) {
        super.a(entityhuman);

        for (int i = 0; i < 4; ++i) {
            ItemStack itemstack = this.a.c_(i);

            if (itemstack != null) {
                entityhuman.b(itemstack);
                this.a.a(i, (ItemStack) null);
            }
        }
    }

    public boolean b(EntityHuman entityhuman) {
        return true;
    }
}

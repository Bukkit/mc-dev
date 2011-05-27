package net.minecraft.server;

public class ContainerFurnace extends Container {

    private TileEntityFurnace a;
    private int b = 0;
    private int c = 0;
    private int h = 0;

    public ContainerFurnace(IInventory iinventory, TileEntityFurnace tileentityfurnace) {
        this.a = tileentityfurnace;
        this.a(new Slot(tileentityfurnace, 0, 56, 17));
        this.a(new Slot(tileentityfurnace, 1, 56, 53));
        this.a(new SlotResult2(tileentityfurnace, 2, 116, 35));

        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.a(new Slot(iinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.a(new Slot(iinventory, i, 8 + i * 18, 142));
        }
    }

    public void a(ICrafting icrafting) {
        super.a(icrafting);
        icrafting.a(this, 0, this.a.cookTime);
        icrafting.a(this, 1, this.a.burnTime);
        icrafting.a(this, 2, this.a.b);
    }

    public void a() {
        super.a();

        for (int i = 0; i < this.g.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.g.get(i);

            if (this.b != this.a.cookTime) {
                icrafting.a(this, 0, this.a.cookTime);
            }

            if (this.c != this.a.burnTime) {
                icrafting.a(this, 1, this.a.burnTime);
            }

            if (this.h != this.a.b) {
                icrafting.a(this, 2, this.a.b);
            }
        }

        this.b = this.a.cookTime;
        this.c = this.a.burnTime;
        this.h = this.a.b;
    }

    public boolean b(EntityHuman entityhuman) {
        return this.a.a_(entityhuman);
    }
}

package net.minecraft.server;

public class ContainerDispenser extends Container {

    private TileEntityDispenser a;

    public ContainerDispenser(IInventory iinventory, TileEntityDispenser tileentitydispenser) {
        this.a = tileentitydispenser;

        int i;
        int j;

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 3; ++j) {
                this.a(new Slot(tileentitydispenser, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.a(new Slot(iinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.a(new Slot(iinventory, i, 8 + i * 18, 142));
        }
    }

    public boolean b(EntityHuman entityhuman) {
        return this.a.a_(entityhuman);
    }
}

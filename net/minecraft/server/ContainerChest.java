package net.minecraft.server;

public class ContainerChest extends Container {

    private IInventory a;

    public ContainerChest(IInventory iinventory, IInventory iinventory1) {
        this.a = iinventory1;
        int i = iinventory1.h_() / 9;
        int j = (i - 4) * 18;

        int k;
        int l;

        for (k = 0; k < i; ++k) {
            for (l = 0; l < 9; ++l) {
                this.a(new Slot(iinventory1, l + k * 9, 8 + l * 18, 18 + k * 18));
            }
        }

        for (k = 0; k < 3; ++k) {
            for (l = 0; l < 9; ++l) {
                this.a(new Slot(iinventory, l + k * 9 + 9, 8 + l * 18, 103 + k * 18 + j));
            }
        }

        for (k = 0; k < 9; ++k) {
            this.a(new Slot(iinventory, k, 8 + k * 18, 161 + j));
        }
    }

    public boolean b(EntityHuman entityhuman) {
        return this.a.a_(entityhuman);
    }
}

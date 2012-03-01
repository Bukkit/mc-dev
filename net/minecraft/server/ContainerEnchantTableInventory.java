package net.minecraft.server;

class ContainerEnchantTableInventory extends ContainerEnchantTableSubcontainer {

    final ContainerEnchantTable enchantTable;

    ContainerEnchantTableInventory(ContainerEnchantTable containerenchanttable, String s, int i) {
        super(s, i);
        this.enchantTable = containerenchanttable;
    }

    public int getMaxStackSize() {
        return 1;
    }

    public void update() {
        super.update();
        this.enchantTable.a((IInventory) this);
    }
}

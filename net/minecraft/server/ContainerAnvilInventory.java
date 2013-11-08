package net.minecraft.server;

class ContainerAnvilInventory extends InventorySubcontainer {

    final ContainerAnvil a;

    ContainerAnvilInventory(ContainerAnvil containeranvil, String s, boolean flag, int i) {
        super(s, flag, i);
        this.a = containeranvil;
    }

    public void update() {
        super.update();
        this.a.a((IInventory) this);
    }
}

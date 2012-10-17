package net.minecraft.server;

class ContainerAnvilInventory extends InventorySubcontainer {

    final ContainerAnvil a;

    ContainerAnvilInventory(ContainerAnvil containeranvil, String s, int i) {
        super(s, i);
        this.a = containeranvil;
    }

    public void update() {
        super.update();
        this.a.a((IInventory) this);
    }
}

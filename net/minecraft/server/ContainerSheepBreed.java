package net.minecraft.server;

class ContainerSheepBreed extends Container {

    final EntitySheep a;

    ContainerSheepBreed(EntitySheep entitysheep) {
        this.a = entitysheep;
    }

    public boolean a(EntityHuman entityhuman) {
        return false;
    }
}

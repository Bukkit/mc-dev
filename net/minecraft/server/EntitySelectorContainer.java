package net.minecraft.server;

final class EntitySelectorContainer implements IEntitySelector {

    EntitySelectorContainer() {}

    public boolean a(Entity entity) {
        return entity instanceof IInventory && entity.isAlive();
    }
}

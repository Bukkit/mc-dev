package net.minecraft.server;

final class EntitySelectorLiving implements IEntitySelector {

    EntitySelectorLiving() {}

    public boolean a(Entity entity) {
        return entity.isAlive();
    }
}

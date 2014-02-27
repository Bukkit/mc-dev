package net.minecraft.server;

final class EntitySelectorChickenJockey implements IEntitySelector {

    EntitySelectorChickenJockey() {}

    public boolean a(Entity entity) {
        return entity.isAlive() && entity.passenger == null && entity.vehicle == null;
    }
}

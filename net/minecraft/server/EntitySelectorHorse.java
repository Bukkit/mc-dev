package net.minecraft.server;

final class EntitySelectorHorse implements IEntitySelector {

    EntitySelectorHorse() {}

    public boolean a(Entity entity) {
        return entity instanceof EntityHorse && ((EntityHorse) entity).cm();
    }
}

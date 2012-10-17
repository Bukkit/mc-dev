package net.minecraft.server;

final class EntitySelectorMonster implements IEntitySelector {

    EntitySelectorMonster() {}

    public boolean a(Entity entity) {
        return entity instanceof IMonster;
    }
}

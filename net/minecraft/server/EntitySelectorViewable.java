package net.minecraft.server;

class EntitySelectorViewable implements IEntitySelector {

    final PathfinderGoalAvoidPlayer d;

    EntitySelectorViewable(PathfinderGoalAvoidPlayer pathfindergoalavoidplayer) {
        this.d = pathfindergoalavoidplayer;
    }

    public boolean a(Entity entity) {
        return entity.isAlive() && PathfinderGoalAvoidPlayer.a(this.d).getEntitySenses().canSee(entity);
    }
}

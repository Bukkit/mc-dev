package net.minecraft.server;

class EntitySelectorViewable implements IEntitySelector {

    final PathfinderGoalAvoidPlayer c;

    EntitySelectorViewable(PathfinderGoalAvoidPlayer pathfindergoalavoidplayer) {
        this.c = pathfindergoalavoidplayer;
    }

    public boolean a(Entity entity) {
        return entity.isAlive() && PathfinderGoalAvoidPlayer.a(this.c).getEntitySenses().canSee(entity);
    }
}

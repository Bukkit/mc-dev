package net.minecraft.server;

class EntitySelectorNearestAttackableTarget implements IEntitySelector {

    final IEntitySelector c;

    final PathfinderGoalNearestAttackableTarget d;

    EntitySelectorNearestAttackableTarget(PathfinderGoalNearestAttackableTarget pathfindergoalnearestattackabletarget, IEntitySelector ientityselector) {
        this.d = pathfindergoalnearestattackabletarget;
        this.c = ientityselector;
    }

    public boolean a(Entity entity) {
        return !(entity instanceof EntityLiving) ? false : (this.c != null && !this.c.a(entity) ? false : this.d.a((EntityLiving) entity, false));
    }
}

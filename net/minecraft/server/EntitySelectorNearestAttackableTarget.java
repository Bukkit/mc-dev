package net.minecraft.server;

class EntitySelectorNearestAttackableTarget implements IEntitySelector {

    final IEntitySelector d;
    final PathfinderGoalNearestAttackableTarget e;

    EntitySelectorNearestAttackableTarget(PathfinderGoalNearestAttackableTarget pathfindergoalnearestattackabletarget, IEntitySelector ientityselector) {
        this.e = pathfindergoalnearestattackabletarget;
        this.d = ientityselector;
    }

    public boolean a(Entity entity) {
        return !(entity instanceof EntityLiving) ? false : (this.d != null && !this.d.a(entity) ? false : this.e.a((EntityLiving) entity, false));
    }
}

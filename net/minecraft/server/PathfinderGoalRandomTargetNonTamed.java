package net.minecraft.server;

public class PathfinderGoalRandomTargetNonTamed extends PathfinderGoalNearestAttackableTarget {

    private EntityTameableAnimal a;

    public PathfinderGoalRandomTargetNonTamed(EntityTameableAnimal entitytameableanimal, Class oclass, int i, boolean flag) {
        super(entitytameableanimal, oclass, i, flag);
        this.a = entitytameableanimal;
    }

    public boolean a() {
        return !this.a.isTamed() && super.a();
    }
}

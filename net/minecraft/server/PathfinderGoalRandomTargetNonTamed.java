package net.minecraft.server;

public class PathfinderGoalRandomTargetNonTamed extends PathfinderGoalNearestAttackableTarget {

    private EntityTameableAnimal g;

    public PathfinderGoalRandomTargetNonTamed(EntityTameableAnimal entitytameableanimal, Class oclass, float f, int i, boolean flag) {
        super(entitytameableanimal, oclass, f, i, flag);
        this.g = entitytameableanimal;
    }

    public boolean a() {
        return this.g.isTamed() ? false : super.a();
    }
}

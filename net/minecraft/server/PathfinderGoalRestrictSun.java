package net.minecraft.server;

public class PathfinderGoalRestrictSun extends PathfinderGoal {

    private EntityCreature a;

    public PathfinderGoalRestrictSun(EntityCreature entitycreature) {
        this.a = entitycreature;
    }

    public boolean a() {
        return this.a.world.v();
    }

    public void c() {
        this.a.getNavigation().d(true);
    }

    public void d() {
        this.a.getNavigation().d(false);
    }
}

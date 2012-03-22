package net.minecraft.server;

public class PathfinderGoalRestrictSun extends PathfinderGoal {

    private EntityCreature a;

    public PathfinderGoalRestrictSun(EntityCreature entitycreature) {
        this.a = entitycreature;
    }

    public boolean a() {
        return this.a.world.e();
    }

    public void c() {
        this.a.al().d(true);
    }

    public void d() {
        this.a.al().d(false);
    }
}

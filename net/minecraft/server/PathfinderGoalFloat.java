package net.minecraft.server;

public class PathfinderGoalFloat extends PathfinderGoal {

    private EntityInsentient a;

    public PathfinderGoalFloat(EntityInsentient entityinsentient) {
        this.a = entityinsentient;
        this.a(4);
        entityinsentient.getNavigation().e(true);
    }

    public boolean a() {
        return this.a.L() || this.a.O();
    }

    public void e() {
        if (this.a.aH().nextFloat() < 0.8F) {
            this.a.getControllerJump().a();
        }
    }
}

package net.minecraft.server;

public class PathfinderGoalFloat extends PathfinderGoal {

    private EntityLiving a;

    public PathfinderGoalFloat(EntityLiving entityliving) {
        this.a = entityliving;
        this.a(4);
        entityliving.getNavigation().e(true);
    }

    public boolean a() {
        return this.a.H() || this.a.J();
    }

    public void d() {
        if (this.a.au().nextFloat() < 0.8F) {
            this.a.getControllerJump().a();
        }
    }
}

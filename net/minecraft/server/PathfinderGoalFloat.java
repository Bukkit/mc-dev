package net.minecraft.server;

public class PathfinderGoalFloat extends PathfinderGoal {

    private EntityLiving a;

    public PathfinderGoalFloat(EntityLiving entityliving) {
        this.a = entityliving;
        this.a(4);
        entityliving.getNavigation().e(true);
    }

    public boolean a() {
        return this.a.G() || this.a.I();
    }

    public void e() {
        if (this.a.aE().nextFloat() < 0.8F) {
            this.a.getControllerJump().a();
        }
    }
}

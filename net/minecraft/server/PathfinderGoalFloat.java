package net.minecraft.server;

public class PathfinderGoalFloat extends PathfinderGoal {

    private EntityLiving a;

    public PathfinderGoalFloat(EntityLiving entityliving) {
        this.a = entityliving;
        this.a(4);
        entityliving.ak().e(true);
    }

    public boolean a() {
        return this.a.aT() || this.a.aU();
    }

    public void e() {
        if (this.a.am().nextFloat() < 0.8F) {
            this.a.getControllerJump().a();
        }
    }
}

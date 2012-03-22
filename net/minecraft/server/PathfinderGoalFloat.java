package net.minecraft.server;

public class PathfinderGoalFloat extends PathfinderGoal {

    private EntityLiving a;

    public PathfinderGoalFloat(EntityLiving entityliving) {
        this.a = entityliving;
        this.a(4);
        entityliving.al().e(true);
    }

    public boolean a() {
        return this.a.aU() || this.a.aV();
    }

    public void e() {
        if (this.a.an().nextFloat() < 0.8F) {
            this.a.getControllerJump().a();
        }
    }
}

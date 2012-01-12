package net.minecraft.server;

public class PathfinderGoalFloat extends PathfinderGoal {

    private EntityLiving a;

    public PathfinderGoalFloat(EntityLiving entityliving) {
        this.a = entityliving;
        this.a(4);
    }

    public boolean a() {
        return this.a.ai().nextFloat() < 0.8F && (this.a.aK() || this.a.aL());
    }

    public void e() {
        this.a.getControllerJump().a();
    }
}

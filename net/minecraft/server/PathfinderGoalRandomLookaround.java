package net.minecraft.server;

public class PathfinderGoalRandomLookaround extends PathfinderGoal {

    private EntityLiving a;
    private double b;
    private double c;
    private int d = 0;

    public PathfinderGoalRandomLookaround(EntityLiving entityliving) {
        this.a = entityliving;
        this.a(3);
    }

    public boolean a() {
        return this.a.aB().nextFloat() < 0.02F;
    }

    public boolean b() {
        return this.d >= 0;
    }

    public void c() {
        double d0 = 6.283185307179586D * this.a.aB().nextDouble();

        this.b = Math.cos(d0);
        this.c = Math.sin(d0);
        this.d = 20 + this.a.aB().nextInt(20);
    }

    public void e() {
        --this.d;
        this.a.getControllerLook().a(this.a.locX + this.b, this.a.locY + (double) this.a.getHeadHeight(), this.a.locZ + this.c, 10.0F, (float) this.a.bp());
    }
}

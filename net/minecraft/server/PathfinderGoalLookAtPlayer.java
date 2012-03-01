package net.minecraft.server;

public class PathfinderGoalLookAtPlayer extends PathfinderGoal {

    private EntityLiving a;
    private EntityLiving b;
    private World c;
    private float d;
    private int e;

    public PathfinderGoalLookAtPlayer(EntityLiving entityliving, World world, float f) {
        this.a = entityliving;
        this.c = world;
        this.d = f;
        this.a(3);
    }

    public boolean a() {
        if (this.a.ai().nextFloat() >= 0.02F) {
            return false;
        } else {
            this.b = this.c.findNearbyPlayer(this.a, (double) this.d);
            return this.b != null;
        }
    }

    public boolean g() {
        return !this.b.isAlive() ? false : (this.a.i(this.b) > (double) (this.d * this.d) ? false : this.e > 0);
    }

    public void e() {
        this.e = 40 + this.a.ai().nextInt(40);
    }

    public void d() {
        this.b = null;
    }

    public void b() {
        this.a.getControllerLook().a(this.b.locX, this.b.locY + (double) this.b.getHeadHeight(), this.b.locZ, 10.0F, (float) this.a.x());
        --this.e;
    }
}

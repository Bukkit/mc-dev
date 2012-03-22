package net.minecraft.server;

public class PathfinderGoalLookAtPlayer extends PathfinderGoal {

    private EntityLiving a;
    private Entity b;
    private float c;
    private int d;
    private float e;
    private Class f;

    public PathfinderGoalLookAtPlayer(EntityLiving entityliving, Class oclass, float f) {
        this.a = entityliving;
        this.f = oclass;
        this.c = f;
        this.e = 0.02F;
        this.a(2);
    }

    public PathfinderGoalLookAtPlayer(EntityLiving entityliving, Class oclass, float f, float f1) {
        this.a = entityliving;
        this.f = oclass;
        this.c = f;
        this.e = f1;
        this.a(2);
    }

    public boolean a() {
        if (this.a.an().nextFloat() >= this.e) {
            return false;
        } else {
            if (this.f == EntityHuman.class) {
                this.b = this.a.world.findNearbyPlayer(this.a, (double) this.c);
            } else {
                this.b = this.a.world.a(this.f, this.a.boundingBox.grow((double) this.c, 3.0D, (double) this.c), this.a);
            }

            return this.b != null;
        }
    }

    public boolean b() {
        return !this.b.isAlive() ? false : (this.a.j(this.b) > (double) (this.c * this.c) ? false : this.d > 0);
    }

    public void c() {
        this.d = 40 + this.a.an().nextInt(40);
    }

    public void d() {
        this.b = null;
    }

    public void e() {
        this.a.getControllerLook().a(this.b.locX, this.b.locY + (double) this.b.getHeadHeight(), this.b.locZ, 10.0F, (float) this.a.D());
        --this.d;
    }
}

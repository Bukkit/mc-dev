package net.minecraft.server;

public class PathfinderGoalLookAtPlayer extends PathfinderGoal {

    private EntityLiving b;
    protected Entity a;
    private float c;
    private int d;
    private float e;
    private Class f;

    public PathfinderGoalLookAtPlayer(EntityLiving entityliving, Class oclass, float f) {
        this.b = entityliving;
        this.f = oclass;
        this.c = f;
        this.e = 0.02F;
        this.a(2);
    }

    public PathfinderGoalLookAtPlayer(EntityLiving entityliving, Class oclass, float f, float f1) {
        this.b = entityliving;
        this.f = oclass;
        this.c = f;
        this.e = f1;
        this.a(2);
    }

    public boolean a() {
        if (this.b.au().nextFloat() >= this.e) {
            return false;
        } else {
            if (this.f == EntityHuman.class) {
                this.a = this.b.world.findNearbyPlayer(this.b, (double) this.c);
            } else {
                this.a = this.b.world.a(this.f, this.b.boundingBox.grow((double) this.c, 3.0D, (double) this.c), this.b);
            }

            return this.a != null;
        }
    }

    public boolean b() {
        return !this.a.isAlive() ? false : (this.b.e(this.a) > (double) (this.c * this.c) ? false : this.d > 0);
    }

    public void e() {
        this.d = 40 + this.b.au().nextInt(40);
    }

    public void c() {
        this.a = null;
    }

    public void d() {
        this.b.getControllerLook().a(this.a.locX, this.a.locY + (double) this.a.getHeadHeight(), this.a.locZ, 10.0F, (float) this.b.bf());
        --this.d;
    }
}

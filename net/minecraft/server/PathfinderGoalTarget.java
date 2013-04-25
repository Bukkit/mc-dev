package net.minecraft.server;

public abstract class PathfinderGoalTarget extends PathfinderGoal {

    protected EntityLiving d;
    protected float e;
    protected boolean f;
    private boolean a;
    private int b;
    private int c;
    private int g;

    public PathfinderGoalTarget(EntityLiving entityliving, float f, boolean flag) {
        this(entityliving, f, flag, false);
    }

    public PathfinderGoalTarget(EntityLiving entityliving, float f, boolean flag, boolean flag1) {
        this.b = 0;
        this.c = 0;
        this.g = 0;
        this.d = entityliving;
        this.e = f;
        this.f = flag;
        this.a = flag1;
    }

    public boolean b() {
        EntityLiving entityliving = this.d.getGoalTarget();

        if (entityliving == null) {
            return false;
        } else if (!entityliving.isAlive()) {
            return false;
        } else if (this.d.e(entityliving) > (double) (this.e * this.e)) {
            return false;
        } else {
            if (this.f) {
                if (this.d.getEntitySenses().canSee(entityliving)) {
                    this.g = 0;
                } else if (++this.g > 60) {
                    return false;
                }
            }

            return true;
        }
    }

    public void c() {
        this.b = 0;
        this.c = 0;
        this.g = 0;
    }

    public void d() {
        this.d.setGoalTarget((EntityLiving) null);
    }

    protected boolean a(EntityLiving entityliving, boolean flag) {
        if (entityliving == null) {
            return false;
        } else if (entityliving == this.d) {
            return false;
        } else if (!entityliving.isAlive()) {
            return false;
        } else if (!this.d.a(entityliving.getClass())) {
            return false;
        } else {
            if (this.d instanceof EntityTameableAnimal && ((EntityTameableAnimal) this.d).isTamed()) {
                if (entityliving instanceof EntityTameableAnimal && ((EntityTameableAnimal) entityliving).isTamed()) {
                    return false;
                }

                if (entityliving == ((EntityTameableAnimal) this.d).getOwner()) {
                    return false;
                }
            } else if (entityliving instanceof EntityHuman && !flag && ((EntityHuman) entityliving).abilities.isInvulnerable) {
                return false;
            }

            if (!this.d.d(MathHelper.floor(entityliving.locX), MathHelper.floor(entityliving.locY), MathHelper.floor(entityliving.locZ))) {
                return false;
            } else if (this.f && !this.d.getEntitySenses().canSee(entityliving)) {
                return false;
            } else {
                if (this.a) {
                    if (--this.c <= 0) {
                        this.b = 0;
                    }

                    if (this.b == 0) {
                        this.b = this.a(entityliving) ? 1 : 2;
                    }

                    if (this.b == 2) {
                        return false;
                    }
                }

                return true;
            }
        }
    }

    private boolean a(EntityLiving entityliving) {
        this.c = 10 + this.d.aE().nextInt(5);
        PathEntity pathentity = this.d.getNavigation().a(entityliving);

        if (pathentity == null) {
            return false;
        } else {
            PathPoint pathpoint = pathentity.c();

            if (pathpoint == null) {
                return false;
            } else {
                int i = pathpoint.a - MathHelper.floor(entityliving.locX);
                int j = pathpoint.c - MathHelper.floor(entityliving.locZ);

                return (double) (i * i + j * j) <= 2.25D;
            }
        }
    }
}

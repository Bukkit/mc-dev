package net.minecraft.server;

public class PathfinderGoalOcelotAttack extends PathfinderGoal {

    World a;
    EntityLiving b;
    EntityLiving c;
    int d = 0;

    public PathfinderGoalOcelotAttack(EntityLiving entityliving) {
        this.b = entityliving;
        this.a = entityliving.world;
        this.a(3);
    }

    public boolean a() {
        EntityLiving entityliving = this.b.getGoalTarget();

        if (entityliving == null) {
            return false;
        } else {
            this.c = entityliving;
            return true;
        }
    }

    public boolean b() {
        return !this.c.isAlive() ? false : (this.b.e(this.c) > 225.0D ? false : !this.b.getNavigation().f() || this.a());
    }

    public void d() {
        this.c = null;
        this.b.getNavigation().g();
    }

    public void e() {
        this.b.getControllerLook().a(this.c, 30.0F, 30.0F);
        double d0 = (double) (this.b.width * 2.0F * this.b.width * 2.0F);
        double d1 = this.b.e(this.c.locX, this.c.boundingBox.b, this.c.locZ);
        float f = 0.23F;

        if (d1 > d0 && d1 < 16.0D) {
            f = 0.4F;
        } else if (d1 < 225.0D) {
            f = 0.18F;
        }

        this.b.getNavigation().a(this.c, f);
        this.d = Math.max(this.d - 1, 0);
        if (d1 <= d0) {
            if (this.d <= 0) {
                this.d = 20;
                this.b.m(this.c);
            }
        }
    }
}

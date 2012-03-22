package net.minecraft.server;

public class PathfinderGoalOzelotAttack extends PathfinderGoal {

    World a;
    EntityLiving b;
    EntityLiving c;
    int d = 0;

    public PathfinderGoalOzelotAttack(EntityLiving entityliving) {
        this.b = entityliving;
        this.a = entityliving.world;
        this.a(3);
    }

    public boolean a() {
        EntityLiving entityliving = this.b.at();

        if (entityliving == null) {
            return false;
        } else {
            this.c = entityliving;
            return true;
        }
    }

    public boolean b() {
        return !this.c.isAlive() ? false : (this.b.j(this.c) > 225.0D ? false : !this.b.al().e() || this.a());
    }

    public void d() {
        this.c = null;
        this.b.al().f();
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

        this.b.al().a(this.c, f);
        this.d = Math.max(this.d - 1, 0);
        if (d1 <= d0) {
            if (this.d <= 0) {
                this.d = 20;
                this.b.a((Entity) this.c);
            }
        }
    }
}

package net.minecraft.server;

public class PathfinderGoalSwell extends PathfinderGoal {

    EntityCreeper a;
    EntityLiving b;

    public PathfinderGoalSwell(EntityCreeper entitycreeper) {
        this.a = entitycreeper;
        this.a(1);
    }

    public boolean a() {
        EntityLiving entityliving = this.a.az();

        return this.a.p() > 0 || entityliving != null && this.a.e(entityliving) < 9.0D;
    }

    public void e() {
        this.a.getNavigation().g();
        this.b = this.a.az();
    }

    public void c() {
        this.b = null;
    }

    public void d() {
        if (this.b == null) {
            this.a.a(-1);
        } else if (this.a.e(this.b) > 49.0D) {
            this.a.a(-1);
        } else if (!this.a.at().canSee(this.b)) {
            this.a.a(-1);
        } else {
            this.a.a(1);
        }
    }
}

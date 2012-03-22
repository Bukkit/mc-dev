package net.minecraft.server;

public class PathfinderGoalSwell extends PathfinderGoal {

    EntityCreeper a;
    EntityLiving b;

    public PathfinderGoalSwell(EntityCreeper entitycreeper) {
        this.a = entitycreeper;
        this.a(1);
    }

    public boolean a() {
        EntityLiving entityliving = this.a.at();

        return this.a.A() > 0 || entityliving != null && this.a.j(entityliving) < 9.0D;
    }

    public void c() {
        this.a.al().f();
        this.b = this.a.at();
    }

    public void d() {
        this.b = null;
    }

    public void e() {
        if (this.b == null) {
            this.a.c(-1);
        } else if (this.a.j(this.b) > 49.0D) {
            this.a.c(-1);
        } else if (!this.a.am().canSee(this.b)) {
            this.a.c(-1);
        } else {
            this.a.c(1);
        }
    }
}

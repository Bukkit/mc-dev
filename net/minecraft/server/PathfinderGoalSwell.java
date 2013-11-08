package net.minecraft.server;

public class PathfinderGoalSwell extends PathfinderGoal {

    EntityCreeper a;
    EntityLiving b;

    public PathfinderGoalSwell(EntityCreeper entitycreeper) {
        this.a = entitycreeper;
        this.a(1);
    }

    public boolean a() {
        EntityLiving entityliving = this.a.getGoalTarget();

        return this.a.bZ() > 0 || entityliving != null && this.a.e(entityliving) < 9.0D;
    }

    public void c() {
        this.a.getNavigation().h();
        this.b = this.a.getGoalTarget();
    }

    public void d() {
        this.b = null;
    }

    public void e() {
        if (this.b == null) {
            this.a.a(-1);
        } else if (this.a.e(this.b) > 49.0D) {
            this.a.a(-1);
        } else if (!this.a.getEntitySenses().canSee(this.b)) {
            this.a.a(-1);
        } else {
            this.a.a(1);
        }
    }
}

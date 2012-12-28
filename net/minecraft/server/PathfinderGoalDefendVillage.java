package net.minecraft.server;

public class PathfinderGoalDefendVillage extends PathfinderGoalTarget {

    EntityIronGolem a;
    EntityLiving b;

    public PathfinderGoalDefendVillage(EntityIronGolem entityirongolem) {
        super(entityirongolem, 16.0F, false, true);
        this.a = entityirongolem;
        this.a(1);
    }

    public boolean a() {
        Village village = this.a.m();

        if (village == null) {
            return false;
        } else {
            this.b = village.b((EntityLiving) this.a);
            if (!this.a(this.b, false)) {
                if (this.d.aB().nextInt(20) == 0) {
                    this.b = village.c(this.a);
                    return this.a(this.b, false);
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
    }

    public void c() {
        this.a.setGoalTarget(this.b);
        super.c();
    }
}

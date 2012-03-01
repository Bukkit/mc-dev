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
        Village village = this.a.l_();

        if (village == null) {
            return false;
        } else {
            this.b = village.b(this.a);
            return this.a(this.b, false);
        }
    }

    public void c() {
        this.a.b(this.b);
        super.c();
    }
}

package net.minecraft.server;

public class PathfinderGoalOwnerHurtTarget extends PathfinderGoalTarget {

    EntityTameableAnimal a;
    EntityLiving b;

    public PathfinderGoalOwnerHurtTarget(EntityTameableAnimal entitytameableanimal) {
        super(entitytameableanimal, 32.0F, false);
        this.a = entitytameableanimal;
        this.a(1);
    }

    public boolean a() {
        if (!this.a.isTamed()) {
            return false;
        } else {
            EntityLiving entityliving = this.a.getOwner();

            if (entityliving == null) {
                return false;
            } else {
                this.b = entityliving.ap();
                return this.a(this.b, false);
            }
        }
    }

    public void c() {
        this.c.b(this.b);
        super.c();
    }
}

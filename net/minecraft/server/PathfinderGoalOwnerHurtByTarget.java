package net.minecraft.server;

public class PathfinderGoalOwnerHurtByTarget extends PathfinderGoalTarget {

    EntityTameableAnimal a;
    EntityLiving b;
    private int e;

    public PathfinderGoalOwnerHurtByTarget(EntityTameableAnimal entitytameableanimal) {
        super(entitytameableanimal, false);
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
                this.b = entityliving.getLastDamager();
                int i = entityliving.aK();

                return i != this.e && this.a(this.b, false) && this.a.a(this.b, entityliving);
            }
        }
    }

    public void c() {
        this.c.setGoalTarget(this.b);
        EntityLiving entityliving = this.a.getOwner();

        if (entityliving != null) {
            this.e = entityliving.aK();
        }

        super.c();
    }
}

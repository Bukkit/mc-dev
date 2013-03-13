package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class PathfinderGoalHurtByTarget extends PathfinderGoalTarget {

    boolean a;
    EntityLiving b;

    public PathfinderGoalHurtByTarget(EntityLiving entityliving, boolean flag) {
        super(entityliving, 16.0F, false);
        this.a = flag;
        this.a(1);
    }

    public boolean a() {
        return this.a(this.d.aF(), true);
    }

    public boolean b() {
        return this.d.aF() != null && this.d.aF() != this.b;
    }

    public void c() {
        this.d.setGoalTarget(this.d.aF());
        this.b = this.d.aF();
        if (this.a) {
            List list = this.d.world.a(this.d.getClass(), AxisAlignedBB.a().a(this.d.locX, this.d.locY, this.d.locZ, this.d.locX + 1.0D, this.d.locY + 1.0D, this.d.locZ + 1.0D).grow((double) this.e, 10.0D, (double) this.e));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityLiving entityliving = (EntityLiving) iterator.next();

                if (this.d != entityliving && entityliving.getGoalTarget() == null) {
                    entityliving.setGoalTarget(this.d.aF());
                }
            }
        }

        super.c();
    }

    public void d() {
        if (this.d.getGoalTarget() != null && this.d.getGoalTarget() instanceof EntityHuman && ((EntityHuman) this.d.getGoalTarget()).abilities.isInvulnerable) {
            super.d();
        }
    }
}

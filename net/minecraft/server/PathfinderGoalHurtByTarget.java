package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class PathfinderGoalHurtByTarget extends PathfinderGoalTarget {

    boolean a;

    public PathfinderGoalHurtByTarget(EntityLiving entityliving, boolean flag) {
        super(entityliving, 16.0F, false);
        this.a = flag;
        this.a(1);
    }

    public boolean a() {
        return this.a(this.c.ao(), true);
    }

    public void c() {
        this.c.b(this.c.ao());
        if (this.a) {
            List list = this.c.world.a(this.c.getClass(), AxisAlignedBB.b(this.c.locX, this.c.locY, this.c.locZ, this.c.locX + 1.0D, this.c.locY + 1.0D, this.c.locZ + 1.0D).grow((double) this.d, 4.0D, (double) this.d));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();
                EntityLiving entityliving = (EntityLiving) entity;

                if (this.c != entityliving && entityliving.at() == null) {
                    entityliving.b(this.c.ao());
                }
            }
        }

        super.c();
    }
}

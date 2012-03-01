package net.minecraft.server;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PathfinderGoalNearestAttackableTarget extends PathfinderGoalTarget {

    EntityLiving a;
    Class b;
    int f;
    private DistanceComparator g;

    public PathfinderGoalNearestAttackableTarget(EntityLiving entityliving, Class oclass, float f, int i, boolean flag) {
        this(entityliving, oclass, f, i, flag, false);
    }

    public PathfinderGoalNearestAttackableTarget(EntityLiving entityliving, Class oclass, float f, int i, boolean flag, boolean flag1) {
        super(entityliving, f, flag, flag1);
        this.b = oclass;
        this.d = f;
        this.f = i;
        this.g = new DistanceComparator(this, entityliving);
        this.a(1);
    }

    public boolean a() {
        if (this.f > 0 && this.c.am().nextInt(this.f) != 0) {
            return false;
        } else {
            if (this.b == EntityHuman.class) {
                EntityHuman entityhuman = this.c.world.findNearbyVulnerablePlayer(this.c, (double) this.d);

                if (this.a(entityhuman, false)) {
                    this.a = entityhuman;
                    return true;
                }
            } else {
                List list = this.c.world.a(this.b, this.c.boundingBox.grow((double) this.d, 4.0D, (double) this.d));

                Collections.sort(list, this.g);
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    Entity entity = (Entity) iterator.next();
                    EntityLiving entityliving = (EntityLiving) entity;

                    if (this.a(entityliving, false)) {
                        this.a = entityliving;
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public void c() {
        this.c.b(this.a);
        super.c();
    }
}

package net.minecraft.server;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PathfinderGoalNearestAttackableTarget extends PathfinderGoalTarget {

    EntityLiving a;
    Class b;
    int c;
    private final IEntitySelector g;
    private DistanceComparator h;

    public PathfinderGoalNearestAttackableTarget(EntityLiving entityliving, Class oclass, float f, int i, boolean flag) {
        this(entityliving, oclass, f, i, flag, false);
    }

    public PathfinderGoalNearestAttackableTarget(EntityLiving entityliving, Class oclass, float f, int i, boolean flag, boolean flag1) {
        this(entityliving, oclass, f, i, flag, flag1, (IEntitySelector) null);
    }

    public PathfinderGoalNearestAttackableTarget(EntityLiving entityliving, Class oclass, float f, int i, boolean flag, boolean flag1, IEntitySelector ientityselector) {
        super(entityliving, f, flag, flag1);
        this.b = oclass;
        this.e = f;
        this.c = i;
        this.h = new DistanceComparator(this, entityliving);
        this.g = ientityselector;
        this.a(1);
    }

    public boolean a() {
        if (this.c > 0 && this.d.aE().nextInt(this.c) != 0) {
            return false;
        } else {
            if (this.b == EntityHuman.class) {
                EntityHuman entityhuman = this.d.world.findNearbyVulnerablePlayer(this.d, (double) this.e);

                if (this.a(entityhuman, false)) {
                    this.a = entityhuman;
                    return true;
                }
            } else {
                List list = this.d.world.a(this.b, this.d.boundingBox.grow((double) this.e, 4.0D, (double) this.e), this.g);

                Collections.sort(list, this.h);
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
        this.d.setGoalTarget(this.a);
        super.c();
    }
}

package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class PathfinderGoalPlay extends PathfinderGoal {

    private EntityVillager a;
    private EntityLiving b;
    private float c;
    private int d;

    public PathfinderGoalPlay(EntityVillager entityvillager, float f) {
        this.a = entityvillager;
        this.c = f;
        this.a(1);
    }

    public boolean a() {
        if (this.a.getAge() >= 0) {
            return false;
        } else if (this.a.am().nextInt(400) != 0) {
            return false;
        } else {
            List list = this.a.world.a(EntityVillager.class, this.a.boundingBox.grow(6.0D, 3.0D, 6.0D));
            double d0 = Double.MAX_VALUE;
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                if (entity != this.a) {
                    EntityVillager entityvillager = (EntityVillager) entity;

                    if (!entityvillager.E_() && entityvillager.getAge() < 0) {
                        double d1 = entityvillager.j(this.a);

                        if (d1 <= d0) {
                            d0 = d1;
                            this.b = entityvillager;
                        }
                    }
                }
            }

            if (this.b == null) {
                Vec3D vec3d = RandomPositionGenerator.a(this.a, 16, 3);

                if (vec3d == null) {
                    return false;
                }
            }

            return true;
        }
    }

    public boolean b() {
        return this.d > 0;
    }

    public void c() {
        if (this.b != null) {
            this.a.b(true);
        }

        this.d = 1000;
    }

    public void d() {
        this.a.b(false);
        this.b = null;
    }

    public void e() {
        --this.d;
        if (this.b != null) {
            if (this.a.j(this.b) > 4.0D) {
                this.a.ak().a(this.b, this.c);
            }
        } else if (this.a.ak().e()) {
            Vec3D vec3d = RandomPositionGenerator.a(this.a, 16, 3);

            if (vec3d == null) {
                return;
            }

            this.a.ak().a(vec3d.a, vec3d.b, vec3d.c, this.c);
        }
    }
}

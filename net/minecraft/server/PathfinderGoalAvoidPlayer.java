package net.minecraft.server;

import java.util.List;

public class PathfinderGoalAvoidPlayer extends PathfinderGoal {

    public final IEntitySelector a = new EntitySelectorViewable(this);
    private EntityCreature b;
    private double c;
    private double d;
    private Entity e;
    private float f;
    private PathEntity g;
    private Navigation h;
    private Class i;

    public PathfinderGoalAvoidPlayer(EntityCreature entitycreature, Class oclass, float f, double d0, double d1) {
        this.b = entitycreature;
        this.i = oclass;
        this.f = f;
        this.c = d0;
        this.d = d1;
        this.h = entitycreature.getNavigation();
        this.a(1);
    }

    public boolean a() {
        if (this.i == EntityHuman.class) {
            if (this.b instanceof EntityTameableAnimal && ((EntityTameableAnimal) this.b).isTamed()) {
                return false;
            }

            this.e = this.b.world.findNearbyPlayer(this.b, (double) this.f);
            if (this.e == null) {
                return false;
            }
        } else {
            List list = this.b.world.a(this.i, this.b.boundingBox.grow((double) this.f, 3.0D, (double) this.f), this.a);

            if (list.isEmpty()) {
                return false;
            }

            this.e = (Entity) list.get(0);
        }

        Vec3D vec3d = RandomPositionGenerator.b(this.b, 16, 7, Vec3D.a(this.e.locX, this.e.locY, this.e.locZ));

        if (vec3d == null) {
            return false;
        } else if (this.e.e(vec3d.a, vec3d.b, vec3d.c) < this.e.f((Entity) this.b)) {
            return false;
        } else {
            this.g = this.h.a(vec3d.a, vec3d.b, vec3d.c);
            return this.g == null ? false : this.g.b(vec3d);
        }
    }

    public boolean b() {
        return !this.h.g();
    }

    public void c() {
        this.h.a(this.g, this.c);
    }

    public void d() {
        this.e = null;
    }

    public void e() {
        if (this.b.f(this.e) < 49.0D) {
            this.b.getNavigation().a(this.d);
        } else {
            this.b.getNavigation().a(this.c);
        }
    }

    static EntityCreature a(PathfinderGoalAvoidPlayer pathfindergoalavoidplayer) {
        return pathfindergoalavoidplayer.b;
    }
}

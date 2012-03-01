package net.minecraft.server;

import java.util.Comparator;

public class DistanceComparator implements Comparator {

    private Entity b;

    final PathfinderGoalNearestAttackableTarget a;

    public DistanceComparator(PathfinderGoalNearestAttackableTarget pathfindergoalnearestattackabletarget, Entity entity) {
        this.a = pathfindergoalnearestattackabletarget;
        this.b = entity;
    }

    public int a(Entity entity, Entity entity1) {
        double d0 = this.b.j(entity);
        double d1 = this.b.j(entity1);

        return d0 < d1 ? -1 : (d0 > d1 ? 1 : 0);
    }
}

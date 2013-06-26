package net.minecraft.server;

import java.util.Comparator;

public class DistanceComparator implements Comparator {

    private final Entity a;

    public DistanceComparator(Entity entity) {
        this.a = entity;
    }

    public int a(Entity entity, Entity entity1) {
        double d0 = this.a.e(entity);
        double d1 = this.a.e(entity1);

        return d0 < d1 ? -1 : (d0 > d1 ? 1 : 0);
    }

    public int compare(Object object, Object object1) {
        return this.a((Entity) object, (Entity) object1);
    }
}

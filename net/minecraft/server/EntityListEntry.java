package net.minecraft.server;

class EntityListEntry {

    final int a;
    Object b;
    EntityListEntry c;
    final int d;

    EntityListEntry(int i, int j, Object object, EntityListEntry entitylistentry) {
        this.b = object;
        this.c = entitylistentry;
        this.a = j;
        this.d = i;
    }

    public final int a() {
        return this.a;
    }

    public final Object b() {
        return this.b;
    }

    public final boolean equals(Object object) {
        if (!(object instanceof EntityListEntry)) {
            return false;
        } else {
            EntityListEntry entitylistentry = (EntityListEntry) object;
            Integer integer = Integer.valueOf(this.a());
            Integer integer1 = Integer.valueOf(entitylistentry.a());

            if (integer == integer1 || integer != null && integer.equals(integer1)) {
                Object object1 = this.b();
                Object object2 = entitylistentry.b();

                if (object1 == object2 || object1 != null && object1.equals(object2)) {
                    return true;
                }
            }

            return false;
        }
    }

    public final int hashCode() {
        return EntityList.f(this.a);
    }

    public final String toString() {
        return this.a() + "=" + this.b();
    }
}

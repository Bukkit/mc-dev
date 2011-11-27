package net.minecraft.server;

class IntHashMapEntry {

    final int a;
    Object b;
    IntHashMapEntry c;
    final int d;

    IntHashMapEntry(int i, int j, Object object, IntHashMapEntry inthashmapentry) {
        this.b = object;
        this.c = inthashmapentry;
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
        if (!(object instanceof IntHashMapEntry)) {
            return false;
        } else {
            IntHashMapEntry inthashmapentry = (IntHashMapEntry) object;
            Integer integer = Integer.valueOf(this.a());
            Integer integer1 = Integer.valueOf(inthashmapentry.a());

            if (integer == integer1 || integer != null && integer.equals(integer1)) {
                Object object1 = this.b();
                Object object2 = inthashmapentry.b();

                if (object1 == object2 || object1 != null && object1.equals(object2)) {
                    return true;
                }
            }

            return false;
        }
    }

    public final int hashCode() {
        return IntHashMap.f(this.a);
    }

    public final String toString() {
        return this.a() + "=" + this.b();
    }
}

package net.minecraft.server;

class LongHashMapEntry {

    final long a;
    Object b;
    LongHashMapEntry c;
    final int d;

    LongHashMapEntry(int i, long j, Object object, LongHashMapEntry longhashmapentry) {
        this.b = object;
        this.c = longhashmapentry;
        this.a = j;
        this.d = i;
    }

    public final long a() {
        return this.a;
    }

    public final Object b() {
        return this.b;
    }

    public final boolean equals(Object object) {
        if (!(object instanceof LongHashMapEntry)) {
            return false;
        } else {
            LongHashMapEntry longhashmapentry = (LongHashMapEntry) object;
            Long olong = Long.valueOf(this.a());
            Long olong1 = Long.valueOf(longhashmapentry.a());

            if (olong == olong1 || olong != null && olong.equals(olong1)) {
                Object object1 = this.b();
                Object object2 = longhashmapentry.b();

                if (object1 == object2 || object1 != null && object1.equals(object2)) {
                    return true;
                }
            }

            return false;
        }
    }

    public final int hashCode() {
        return LongHashMap.f(this.a);
    }

    public final String toString() {
        return this.a() + "=" + this.b();
    }
}

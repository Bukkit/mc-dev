package net.minecraft.server;

public class NextTickListEntry implements Comparable {

    private static long g = 0L;
    public int a;
    public int b;
    public int c;
    public int d;
    public long e;
    public int f;
    private long h;

    public NextTickListEntry(int i, int j, int k, int l) {
        this.h = (long) (g++);
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = l;
    }

    public boolean equals(Object object) {
        if (!(object instanceof NextTickListEntry)) {
            return false;
        } else {
            NextTickListEntry nextticklistentry = (NextTickListEntry) object;

            return this.a == nextticklistentry.a && this.b == nextticklistentry.b && this.c == nextticklistentry.c && this.d == nextticklistentry.d;
        }
    }

    public int hashCode() {
        return (this.a * 1024 * 1024 + this.c * 1024 + this.b) * 256 + this.d;
    }

    public NextTickListEntry a(long i) {
        this.e = i;
        return this;
    }

    public void a(int i) {
        this.f = i;
    }

    public int compareTo(NextTickListEntry nextticklistentry) {
        return this.e < nextticklistentry.e ? -1 : (this.e > nextticklistentry.e ? 1 : (this.f != nextticklistentry.f ? this.f - nextticklistentry.f : (this.h < nextticklistentry.h ? -1 : (this.h > nextticklistentry.h ? 1 : 0))));
    }

    public int compareTo(Object object) {
        return this.compareTo((NextTickListEntry) object);
    }
}

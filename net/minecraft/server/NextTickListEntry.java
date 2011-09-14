package net.minecraft.server;

public class NextTickListEntry implements Comparable {

    private static long f = 0L;
    public int a;
    public int b;
    public int c;
    public int d;
    public long e;
    private long g;

    public NextTickListEntry(int i, int j, int k, int l) {
        this.g = (long) (f++);
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

    public int compareTo(NextTickListEntry nextticklistentry) {
        return this.e < nextticklistentry.e ? -1 : (this.e > nextticklistentry.e ? 1 : (this.g < nextticklistentry.g ? -1 : (this.g > nextticklistentry.g ? 1 : 0)));
    }
}

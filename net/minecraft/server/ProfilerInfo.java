package net.minecraft.server;

public final class ProfilerInfo implements Comparable {

    public double a;
    public double b;
    public String c;

    public ProfilerInfo(String s, double d0, double d1) {
        this.c = s;
        this.a = d0;
        this.b = d1;
    }

    public int a(ProfilerInfo profilerinfo) {
        return profilerinfo.a < this.a ? -1 : (profilerinfo.a > this.a ? 1 : profilerinfo.c.compareTo(this.c));
    }

    public int compareTo(Object object) {
        return this.a((ProfilerInfo) object);
    }
}

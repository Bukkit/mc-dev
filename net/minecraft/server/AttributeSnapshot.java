package net.minecraft.server;

import java.util.Collection;

public class AttributeSnapshot {

    private final String b;
    private final double c;
    private final Collection d;

    final Packet44UpdateAttributes a;

    public AttributeSnapshot(Packet44UpdateAttributes packet44updateattributes, String s, double d0, Collection collection) {
        this.a = packet44updateattributes;
        this.b = s;
        this.c = d0;
        this.d = collection;
    }

    public String a() {
        return this.b;
    }

    public double b() {
        return this.c;
    }

    public Collection c() {
        return this.d;
    }
}

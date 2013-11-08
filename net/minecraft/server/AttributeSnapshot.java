package net.minecraft.server;

import java.util.Collection;

public class AttributeSnapshot {

    private final String b;
    private final double c;
    private final Collection d;
    final PacketPlayOutUpdateAttributes a;

    public AttributeSnapshot(PacketPlayOutUpdateAttributes packetplayoutupdateattributes, String s, double d0, Collection collection) {
        this.a = packetplayoutupdateattributes;
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

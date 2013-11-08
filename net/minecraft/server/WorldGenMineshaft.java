package net.minecraft.server;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class WorldGenMineshaft extends StructureGenerator {

    private double e = 0.004D;

    public WorldGenMineshaft() {}

    public String a() {
        return "Mineshaft";
    }

    public WorldGenMineshaft(Map map) {
        Iterator iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();

            if (((String) entry.getKey()).equals("chance")) {
                this.e = MathHelper.a((String) entry.getValue(), this.e);
            }
        }
    }

    protected boolean a(int i, int j) {
        return this.b.nextDouble() < this.e && this.b.nextInt(80) < Math.max(Math.abs(i), Math.abs(j));
    }

    protected StructureStart b(int i, int j) {
        return new WorldGenMineshaftStart(this.c, this.b, i, j);
    }
}

package net.minecraft.server;

import java.util.Iterator;
import java.util.Map;

import net.minecraft.util.com.google.common.collect.BiMap;
import net.minecraft.util.com.google.common.collect.HashBiMap;

public class RegistryMaterials extends RegistrySimple implements Registry {

    protected final RegistryID a = new RegistryID();
    protected final Map b;

    public RegistryMaterials() {
        this.b = ((BiMap) this.c).inverse();
    }

    public void a(int i, String s, Object object) {
        this.a.a(object, i);
        this.a(c(s), object);
    }

    protected Map a() {
        return HashBiMap.create();
    }

    public Object get(String s) {
        return super.get(c(s));
    }

    public String c(Object object) {
        return (String) this.b.get(object);
    }

    public boolean b(String s) {
        return super.d(c(s));
    }

    public int b(Object object) {
        return this.a.b(object);
    }

    public Object a(int i) {
        return this.a.a(i);
    }

    public Iterator iterator() {
        return this.a.iterator();
    }

    public boolean b(int i) {
        return this.a.b(i);
    }

    private static String c(String s) {
        return s.indexOf(58) == -1 ? "minecraft:" + s : s;
    }

    public boolean d(Object object) {
        return this.b((String) object);
    }

    public Object get(Object object) {
        return this.get((String) object);
    }
}

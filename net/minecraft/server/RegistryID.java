package net.minecraft.server;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.util.com.google.common.base.Predicates;
import net.minecraft.util.com.google.common.collect.Iterators;
import net.minecraft.util.com.google.common.collect.Lists;

public class RegistryID implements Registry {

    private IdentityHashMap a = new IdentityHashMap(512);
    private List b = Lists.newArrayList();

    public RegistryID() {}

    public void a(Object object, int i) {
        this.a.put(object, Integer.valueOf(i));

        while (this.b.size() <= i) {
            this.b.add(null);
        }

        this.b.set(i, object);
    }

    public int b(Object object) {
        Integer integer = (Integer) this.a.get(object);

        return integer == null ? -1 : integer.intValue();
    }

    public Object a(int i) {
        return i >= 0 && i < this.b.size() ? this.b.get(i) : null;
    }

    public Iterator iterator() {
        return Iterators.filter(this.b.iterator(), Predicates.notNull());
    }

    public boolean b(int i) {
        return this.a(i) != null;
    }
}

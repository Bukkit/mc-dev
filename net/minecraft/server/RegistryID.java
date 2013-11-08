package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.util.com.google.common.base.Predicates;
import net.minecraft.util.com.google.common.collect.Iterators;
import net.minecraft.util.gnu.trove.map.hash.TIntIntHashMap;

public class RegistryID implements Registry {

    private TIntIntHashMap a = new TIntIntHashMap(256, 0.5F, -1, -1);
    private List b = new ArrayList();

    public RegistryID() {}

    public void a(Object object, int i) {
        this.a.put(System.identityHashCode(object), i);

        while (this.b.size() <= i) {
            this.b.add(null);
        }

        this.b.set(i, object);
    }

    public int b(Object object) {
        return this.a.get(System.identityHashCode(object));
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

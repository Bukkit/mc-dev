package net.minecraft.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.util.com.google.common.collect.Multimap;

public abstract class AttributeMapBase {

    protected final Map a = new HashMap();
    protected final Map b = new InsensitiveStringMap();

    public AttributeMapBase() {}

    public AttributeInstance a(IAttribute iattribute) {
        return (AttributeInstance) this.a.get(iattribute);
    }

    public AttributeInstance a(String s) {
        return (AttributeInstance) this.b.get(s);
    }

    public abstract AttributeInstance b(IAttribute iattribute);

    public Collection a() {
        return this.b.values();
    }

    public void a(AttributeModifiable attributemodifiable) {}

    public void a(Multimap multimap) {
        Iterator iterator = multimap.entries().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            AttributeInstance attributeinstance = this.a((String) entry.getKey());

            if (attributeinstance != null) {
                attributeinstance.b((AttributeModifier) entry.getValue());
            }
        }
    }

    public void b(Multimap multimap) {
        Iterator iterator = multimap.entries().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            AttributeInstance attributeinstance = this.a((String) entry.getKey());

            if (attributeinstance != null) {
                attributeinstance.b((AttributeModifier) entry.getValue());
                attributeinstance.a((AttributeModifier) entry.getValue());
            }
        }
    }
}

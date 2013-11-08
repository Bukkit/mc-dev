package net.minecraft.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.minecraft.util.com.google.common.collect.Sets;

public class AttributeMapServer extends AttributeMapBase {

    private final Set d = Sets.newHashSet();
    protected final Map c = new InsensitiveStringMap();

    public AttributeMapServer() {}

    public AttributeModifiable c(IAttribute iattribute) {
        return (AttributeModifiable) super.a(iattribute);
    }

    public AttributeModifiable b(String s) {
        AttributeInstance attributeinstance = super.a(s);

        if (attributeinstance == null) {
            attributeinstance = (AttributeInstance) this.c.get(s);
        }

        return (AttributeModifiable) attributeinstance;
    }

    public AttributeInstance b(IAttribute iattribute) {
        if (this.b.containsKey(iattribute.a())) {
            throw new IllegalArgumentException("Attribute is already registered!");
        } else {
            AttributeModifiable attributemodifiable = new AttributeModifiable(this, iattribute);

            this.b.put(iattribute.a(), attributemodifiable);
            if (iattribute instanceof AttributeRanged && ((AttributeRanged) iattribute).f() != null) {
                this.c.put(((AttributeRanged) iattribute).f(), attributemodifiable);
            }

            this.a.put(iattribute, attributemodifiable);
            return attributemodifiable;
        }
    }

    public void a(AttributeModifiable attributemodifiable) {
        if (attributemodifiable.a().c()) {
            this.d.add(attributemodifiable);
        }
    }

    public Set b() {
        return this.d;
    }

    public Collection c() {
        HashSet hashset = Sets.newHashSet();
        Iterator iterator = this.a().iterator();

        while (iterator.hasNext()) {
            AttributeInstance attributeinstance = (AttributeInstance) iterator.next();

            if (attributeinstance.a().c()) {
                hashset.add(attributeinstance);
            }
        }

        return hashset;
    }

    public AttributeInstance a(String s) {
        return this.b(s);
    }

    public AttributeInstance a(IAttribute iattribute) {
        return this.c(iattribute);
    }
}

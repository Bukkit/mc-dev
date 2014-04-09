package net.minecraft.server;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import net.minecraft.util.com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrySimple implements IRegistry {

    private static final Logger a = LogManager.getLogger();
    protected final Map c = this.a();

    public RegistrySimple() {}

    protected Map a() {
        return Maps.newHashMap();
    }

    public Object get(Object object) {
        return this.c.get(object);
    }

    public void a(Object object, Object object1) {
        if (this.c.containsKey(object)) {
            a.debug("Adding duplicate key \'" + object + "\' to registry");
        }

        this.c.put(object, object1);
    }

    public Set keySet() {
        return Collections.unmodifiableSet(this.c.keySet());
    }

    public boolean d(Object object) {
        return this.c.containsKey(object);
    }
}

package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;

public class RegistrySimple implements IRegistry {

    protected final Map a = this.a();

    public RegistrySimple() {}

    protected HashMap a() {
        return Maps.newHashMap();
    }

    public Object a(Object object) {
        return this.a.get(object);
    }

    public void a(Object object, Object object1) {
        this.a.put(object, object1);
    }
}

package net.minecraft.server;

public class RegistryDefault extends RegistrySimple {

    private final Object a;

    public RegistryDefault(Object object) {
        this.a = object;
    }

    public Object get(Object object) {
        Object object1 = super.get(object);

        return object1 == null ? this.a : object1;
    }
}

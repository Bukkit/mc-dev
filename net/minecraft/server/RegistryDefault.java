package net.minecraft.server;

public class RegistryDefault extends RegistrySimple {

    private final Object a;

    public RegistryDefault(Object object) {
        this.a = object;
    }

    public Object a(Object object) {
        Object object1 = super.a(object);

        return object1 == null ? this.a : object1;
    }
}

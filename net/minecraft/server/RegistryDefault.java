package net.minecraft.server;

public class RegistryDefault extends RegistrySimple {

    private final Object b;

    public RegistryDefault(Object object) {
        this.b = object;
    }

    public Object a(Object object) {
        Object object1 = super.a(object);

        return object1 == null ? this.b : object1;
    }
}

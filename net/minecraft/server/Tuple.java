package net.minecraft.server;

public class Tuple {

    private Object a;
    private Object b;

    public Tuple(Object object, Object object1) {
        this.a = object;
        this.b = object1;
    }

    public Object a() {
        return this.a;
    }

    public Object b() {
        return this.b;
    }
}

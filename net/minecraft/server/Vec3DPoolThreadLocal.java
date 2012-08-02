package net.minecraft.server;

final class Vec3DPoolThreadLocal extends ThreadLocal {

    Vec3DPoolThreadLocal() {}

    protected Vec3DPool a() {
        return new Vec3DPool(300, 2000);
    }

    protected Object initialValue() {
        return this.a();
    }
}

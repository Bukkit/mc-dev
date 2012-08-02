package net.minecraft.server;

final class AABBPoolThreadLocal extends ThreadLocal {

    AABBPoolThreadLocal() {}

    protected AABBPool a() {
        return new AABBPool(300, 2000);
    }

    protected Object initialValue() {
        return this.a();
    }
}

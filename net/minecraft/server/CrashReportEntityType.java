package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportEntityType implements Callable {

    final Entity a;

    CrashReportEntityType(Entity entity) {
        this.a = entity;
    }

    public String a() {
        return EntityTypes.b(this.a) + " (" + this.a.getClass().getCanonicalName() + ")";
    }

    public Object call() {
        return this.a();
    }
}

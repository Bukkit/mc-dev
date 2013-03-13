package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportEntityName implements Callable {

    final Entity a;

    CrashReportEntityName(Entity entity) {
        this.a = entity;
    }

    public String a() {
        return this.a.getLocalizedName();
    }

    public Object call() {
        return this.a();
    }
}

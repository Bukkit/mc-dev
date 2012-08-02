package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportEntities implements Callable {

    final World a;

    CrashReportEntities(World world) {
        this.a = world;
    }

    public String a() {
        return this.a.entityList.size() + " total; " + this.a.entityList.toString();
    }

    public Object call() {
        return this.a();
    }
}

package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportEffectID implements Callable {

    final MobEffect a;

    final EntityLiving b;

    CrashReportEffectID(EntityLiving entityliving, MobEffect mobeffect) {
        this.b = entityliving;
        this.a = mobeffect;
    }

    public String a() {
        return this.a.getEffectId() + "";
    }

    public Object call() {
        return this.a();
    }
}

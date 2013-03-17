package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportEffectDuration implements Callable {

    final MobEffect a;

    final EntityLiving b;

    CrashReportEffectDuration(EntityLiving entityliving, MobEffect mobeffect) {
        this.b = entityliving;
        this.a = mobeffect;
    }

    public String a() {
        return this.a.getDuration() + "";
    }

    public Object call() {
        return this.a();
    }
}

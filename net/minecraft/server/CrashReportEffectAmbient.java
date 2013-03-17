package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportEffectAmbient implements Callable {

    final MobEffect a;

    final EntityLiving b;

    CrashReportEffectAmbient(EntityLiving entityliving, MobEffect mobeffect) {
        this.b = entityliving;
        this.a = mobeffect;
    }

    public String a() {
        return this.a.isAmbient() + "";
    }

    public Object call() {
        return this.a();
    }
}

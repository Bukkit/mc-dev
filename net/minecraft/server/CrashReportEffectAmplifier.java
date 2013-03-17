package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportEffectAmplifier implements Callable {

    final MobEffect a;

    final EntityLiving b;

    CrashReportEffectAmplifier(EntityLiving entityliving, MobEffect mobeffect) {
        this.b = entityliving;
        this.a = mobeffect;
    }

    public String a() {
        return this.a.getAmplifier() + "";
    }

    public Object call() {
        return this.a();
    }
}

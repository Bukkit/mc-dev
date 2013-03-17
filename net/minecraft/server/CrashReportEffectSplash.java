package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportEffectSplash implements Callable {

    final MobEffect a;

    final EntityLiving b;

    CrashReportEffectSplash(EntityLiving entityliving, MobEffect mobeffect) {
        this.b = entityliving;
        this.a = mobeffect;
    }

    public String a() {
        return this.a.isSplash() + "";
    }

    public Object call() {
        return this.a();
    }
}

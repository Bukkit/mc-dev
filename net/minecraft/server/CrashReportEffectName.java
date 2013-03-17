package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportEffectName implements Callable {

    final MobEffect a;

    final EntityLiving b;

    CrashReportEffectName(EntityLiving entityliving, MobEffect mobeffect) {
        this.b = entityliving;
        this.a = mobeffect;
    }

    public String a() {
        return MobEffectList.byId[this.a.getEffectId()].a();
    }

    public Object call() {
        return this.a();
    }
}

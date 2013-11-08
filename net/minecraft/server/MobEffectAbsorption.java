package net.minecraft.server;

public class MobEffectAbsorption extends MobEffectList {

    protected MobEffectAbsorption(int i, boolean flag, int j) {
        super(i, flag, j);
    }

    public void a(EntityLiving entityliving, AttributeMapBase attributemapbase, int i) {
        entityliving.m(entityliving.bs() - (float) (4 * (i + 1)));
        super.a(entityliving, attributemapbase, i);
    }

    public void b(EntityLiving entityliving, AttributeMapBase attributemapbase, int i) {
        entityliving.m(entityliving.bs() + (float) (4 * (i + 1)));
        super.b(entityliving, attributemapbase, i);
    }
}

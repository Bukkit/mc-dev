package net.minecraft.server;

public class CombatEntry {

    private final DamageSource a;
    private final int b;
    private final float c;
    private final float d;
    private final String e;
    private final float f;

    public CombatEntry(DamageSource damagesource, int i, float f, float f1, String s, float f2) {
        this.a = damagesource;
        this.b = i;
        this.c = f1;
        this.d = f;
        this.e = s;
        this.f = f2;
    }

    public DamageSource a() {
        return this.a;
    }

    public float c() {
        return this.c;
    }

    public boolean f() {
        return this.a.getEntity() instanceof EntityLiving;
    }

    public String g() {
        return this.e;
    }

    public IChatBaseComponent h() {
        return this.a().getEntity() == null ? null : this.a().getEntity().getScoreboardDisplayName();
    }

    public float i() {
        return this.a == DamageSource.OUT_OF_WORLD ? Float.MAX_VALUE : this.f;
    }
}

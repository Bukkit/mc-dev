package net.minecraft.server;

public class CombatEntry {

    private final DamageSource a;
    private final int b;
    private final int c;
    private final int d;
    private final String e;
    private final float f;

    public CombatEntry(DamageSource damagesource, int i, int j, int k, String s, float f) {
        this.a = damagesource;
        this.b = i;
        this.c = k;
        this.d = j;
        this.e = s;
        this.f = f;
    }

    public DamageSource a() {
        return this.a;
    }

    public int c() {
        return this.c;
    }

    public boolean f() {
        return this.a.getEntity() instanceof EntityLiving;
    }

    public String g() {
        return this.e;
    }

    public String h() {
        return this.a().getEntity() == null ? null : this.a().getEntity().getScoreboardDisplayName();
    }

    public float i() {
        return this.a == DamageSource.OUT_OF_WORLD ? Float.MAX_VALUE : this.f;
    }
}

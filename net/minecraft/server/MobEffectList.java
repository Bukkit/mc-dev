package net.minecraft.server;

public class MobEffectList {

    public static final MobEffectList[] a = new MobEffectList[32];
    public static final MobEffectList b = null;
    public static final MobEffectList c = (new MobEffectList(1)).a("potion.moveSpeed");
    public static final MobEffectList d = (new MobEffectList(2)).a("potion.moveSlowdown");
    public static final MobEffectList e = (new MobEffectList(3)).a("potion.digSpeed");
    public static final MobEffectList f = (new MobEffectList(4)).a("potion.digSlowDown");
    public static final MobEffectList g = (new MobEffectList(5)).a("potion.damageBoost");
    public static final MobEffectList h = (new InstantMobEffect(6)).a("potion.heal");
    public static final MobEffectList i = (new InstantMobEffect(7)).a("potion.harm");
    public static final MobEffectList j = (new MobEffectList(8)).a("potion.jump");
    public static final MobEffectList k = (new MobEffectList(9)).a("potion.confusion");
    public static final MobEffectList l = (new MobEffectList(10)).a("potion.regeneration");
    public static final MobEffectList m = (new MobEffectList(11)).a("potion.resistance");
    public static final MobEffectList n = (new MobEffectList(12)).a("potion.fireResistance");
    public static final MobEffectList o = (new MobEffectList(13)).a("potion.waterBreathing");
    public static final MobEffectList p = (new MobEffectList(14)).a("potion.invisibility");
    public static final MobEffectList q = (new MobEffectList(15)).a("potion.blindness");
    public static final MobEffectList r = (new MobEffectList(16)).a("potion.nightVision");
    public static final MobEffectList s = (new MobEffectList(17)).a("potion.hunger");
    public static final MobEffectList t = (new MobEffectList(18)).a("potion.weakness");
    public static final MobEffectList u = (new MobEffectList(19)).a("potion.poison");
    public static final MobEffectList v = null;
    public static final MobEffectList w = null;
    public static final MobEffectList x = null;
    public static final MobEffectList y = null;
    public static final MobEffectList z = null;
    public static final MobEffectList A = null;
    public static final MobEffectList B = null;
    public static final MobEffectList C = null;
    public static final MobEffectList D = null;
    public static final MobEffectList E = null;
    public static final MobEffectList F = null;
    public static final MobEffectList G = null;
    public final int H;
    private String I = "";

    protected MobEffectList(int i) {
        this.H = i;
        a[i] = this;
    }

    public void a(EntityLiving entityliving, int i) {
        if (this.H == l.H) {
            if (entityliving.health < 20) {
                entityliving.c(1);
            }
        } else if (this.H == u.H) {
            if (entityliving.health > 1) {
                entityliving.damageEntity(DamageSource.l, 1);
            }
        } else if (this.H == s.H && entityliving instanceof EntityHuman) {
            ((EntityHuman) entityliving).b(0.025F * (float) (i + 1));
        } else if (this.H == h.H) {
            entityliving.c(4 << i);
        } else if (this.H == i.H) {
            entityliving.damageEntity(DamageSource.l, 4 << i);
        }
    }

    public boolean a(int i, int j) {
        if (this.H != l.H && this.H != u.H) {
            return this.H == s.H;
        } else {
            int k = 25 >> j;

            return k > 0 ? i % k == 0 : true;
        }
    }

    public MobEffectList a(String s) {
        this.I = s;
        return this;
    }
}

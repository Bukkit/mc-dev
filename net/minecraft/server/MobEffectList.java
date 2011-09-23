package net.minecraft.server;

public class MobEffectList {

    public static final MobEffectList[] byId = new MobEffectList[32];
    public static final MobEffectList b = null;
    public static final MobEffectList FASTER_MOVEMENT = (new MobEffectList(1)).a("potion.moveSpeed");
    public static final MobEffectList SLOWER_MOVEMENT = (new MobEffectList(2)).a("potion.moveSlowdown");
    public static final MobEffectList FASTER_DIG = (new MobEffectList(3)).a("potion.digSpeed");
    public static final MobEffectList SLOWER_DIG = (new MobEffectList(4)).a("potion.digSlowDown");
    public static final MobEffectList INCREASE_DAMAGE = (new MobEffectList(5)).a("potion.damageBoost");
    public static final MobEffectList HEAL = (new InstantMobEffect(6)).a("potion.heal");
    public static final MobEffectList HARM = (new InstantMobEffect(7)).a("potion.harm");
    public static final MobEffectList JUMP = (new MobEffectList(8)).a("potion.jump");
    public static final MobEffectList CONFUSION = (new MobEffectList(9)).a("potion.confusion");
    public static final MobEffectList REGENERATION = (new MobEffectList(10)).a("potion.regeneration");
    public static final MobEffectList RESISTANCE = (new MobEffectList(11)).a("potion.resistance");
    public static final MobEffectList FIRE_RESISTANCE = (new MobEffectList(12)).a("potion.fireResistance");
    public static final MobEffectList WATER_BREATHING = (new MobEffectList(13)).a("potion.waterBreathing");
    public static final MobEffectList INVISIBILITY = (new MobEffectList(14)).a("potion.invisibility");
    public static final MobEffectList BLINDNESS = (new MobEffectList(15)).a("potion.blindness");
    public static final MobEffectList NIGHT_VISION = (new MobEffectList(16)).a("potion.nightVision");
    public static final MobEffectList HUNGER = (new MobEffectList(17)).a("potion.hunger");
    public static final MobEffectList WEAKNESS = (new MobEffectList(18)).a("potion.weakness");
    public static final MobEffectList POISON = (new MobEffectList(19)).a("potion.poison");
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
    public final int id;
    private String I = "";

    protected MobEffectList(int i) {
        this.id = i;
        byId[i] = this;
    }

    public void tick(EntityLiving entityliving, int i) {
        if (this.id == REGENERATION.id) {
            if (entityliving.health < 20) {
                entityliving.c(1);
            }
        } else if (this.id == POISON.id) {
            if (entityliving.health > 1) {
                entityliving.damageEntity(DamageSource.MAGIC, 1);
            }
        } else if (this.id == HUNGER.id && entityliving instanceof EntityHuman) {
            ((EntityHuman) entityliving).b(0.025F * (float) (i + 1));
        } else if (this.id == HEAL.id) {
            entityliving.c(4 << i);
        } else if (this.id == HARM.id) {
            entityliving.damageEntity(DamageSource.MAGIC, 4 << i);
        }
    }

    public boolean a(int i, int j) {
        if (this.id != REGENERATION.id && this.id != POISON.id) {
            return this.id == HUNGER.id;
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

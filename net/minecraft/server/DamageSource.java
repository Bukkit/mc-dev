package net.minecraft.server;

public class DamageSource {

    public static DamageSource FIRE = (new DamageSource("inFire")).j();
    public static DamageSource BURN = (new DamageSource("onFire")).h().j();
    public static DamageSource LAVA = (new DamageSource("lava")).j();
    public static DamageSource STUCK = (new DamageSource("inWall")).h();
    public static DamageSource DROWN = (new DamageSource("drown")).h();
    public static DamageSource STARVE = (new DamageSource("starve")).h();
    public static DamageSource CACTUS = new DamageSource("cactus");
    public static DamageSource FALL = (new DamageSource("fall")).h();
    public static DamageSource OUT_OF_WORLD = (new DamageSource("outOfWorld")).h().i();
    public static DamageSource GENERIC = (new DamageSource("generic")).h();
    public static DamageSource EXPLOSION = new DamageSource("explosion");
    public static DamageSource MAGIC = (new DamageSource("magic")).h();
    private boolean a = false;
    private boolean o = false;
    private float p = 0.3F;
    private boolean q;
    private boolean r;
    public String translationIndex;

    public static DamageSource mobAttack(EntityLiving entityliving) {
        return new EntityDamageSource("mob", entityliving);
    }

    public static DamageSource playerAttack(EntityHuman entityhuman) {
        return new EntityDamageSource("player", entityhuman);
    }

    public static DamageSource arrow(EntityArrow entityarrow, Entity entity) {
        return (new EntityDamageSourceIndirect("arrow", entityarrow, entity)).d();
    }

    public static DamageSource fireball(EntityFireball entityfireball, Entity entity) {
        return (new EntityDamageSourceIndirect("fireball", entityfireball, entity)).j().d();
    }

    public static DamageSource projectile(Entity entity, Entity entity1) {
        return (new EntityDamageSourceIndirect("thrown", entity, entity1)).d();
    }

    public static DamageSource b(Entity entity, Entity entity1) {
        return (new EntityDamageSourceIndirect("indirectMagic", entity, entity1)).h();
    }

    public boolean c() {
        return this.r;
    }

    public DamageSource d() {
        this.r = true;
        return this;
    }

    public boolean ignoresArmor() {
        return this.a;
    }

    public float f() {
        return this.p;
    }

    public boolean ignoresInvulnerability() {
        return this.o;
    }

    protected DamageSource(String s) {
        this.translationIndex = s;
    }

    public Entity b() {
        return this.getEntity();
    }

    public Entity getEntity() {
        return null;
    }

    protected DamageSource h() {
        this.a = true;
        this.p = 0.0F;
        return this;
    }

    protected DamageSource i() {
        this.o = true;
        return this;
    }

    protected DamageSource j() {
        this.q = true;
        return this;
    }

    public String getLocalizedDeathMessage(EntityHuman entityhuman) {
        return LocaleI18n.get("death." + this.translationIndex, new Object[] { entityhuman.name});
    }

    public boolean k() {
        return this.q;
    }

    public String l() {
        return this.translationIndex;
    }
}

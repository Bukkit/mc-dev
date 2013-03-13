package net.minecraft.server;

public class DamageSource {

    public static DamageSource FIRE = (new DamageSource("inFire")).l();
    public static DamageSource BURN = (new DamageSource("onFire")).j().l();
    public static DamageSource LAVA = (new DamageSource("lava")).l();
    public static DamageSource STUCK = (new DamageSource("inWall")).j();
    public static DamageSource DROWN = (new DamageSource("drown")).j();
    public static DamageSource STARVE = (new DamageSource("starve")).j();
    public static DamageSource CACTUS = new DamageSource("cactus");
    public static DamageSource FALL = (new DamageSource("fall")).j();
    public static DamageSource OUT_OF_WORLD = (new DamageSource("outOfWorld")).j().k();
    public static DamageSource GENERIC = (new DamageSource("generic")).j();
    public static DamageSource MAGIC = (new DamageSource("magic")).j().r();
    public static DamageSource WITHER = (new DamageSource("wither")).j();
    public static DamageSource ANVIL = new DamageSource("anvil");
    public static DamageSource FALLING_BLOCK = new DamageSource("fallingBlock");
    private boolean p = false;
    private boolean q = false;
    private float r = 0.3F;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v = false;
    private boolean w = false;
    public String translationIndex;

    public static DamageSource mobAttack(EntityLiving entityliving) {
        return new EntityDamageSource("mob", entityliving);
    }

    public static DamageSource playerAttack(EntityHuman entityhuman) {
        return new EntityDamageSource("player", entityhuman);
    }

    public static DamageSource arrow(EntityArrow entityarrow, Entity entity) {
        return (new EntityDamageSourceIndirect("arrow", entityarrow, entity)).b();
    }

    public static DamageSource fireball(EntityFireball entityfireball, Entity entity) {
        return entity == null ? (new EntityDamageSourceIndirect("onFire", entityfireball, entityfireball)).l().b() : (new EntityDamageSourceIndirect("fireball", entityfireball, entity)).l().b();
    }

    public static DamageSource projectile(Entity entity, Entity entity1) {
        return (new EntityDamageSourceIndirect("thrown", entity, entity1)).b();
    }

    public static DamageSource b(Entity entity, Entity entity1) {
        return (new EntityDamageSourceIndirect("indirectMagic", entity, entity1)).j().r();
    }

    public static DamageSource a(Entity entity) {
        return (new EntityDamageSource("thorns", entity)).r();
    }

    public static DamageSource explosion(Explosion explosion) {
        return explosion != null && explosion.c() != null ? (new EntityDamageSource("explosion.player", explosion.c())).o().d() : (new DamageSource("explosion")).o().d();
    }

    public boolean a() {
        return this.t;
    }

    public DamageSource b() {
        this.t = true;
        return this;
    }

    public boolean c() {
        return this.w;
    }

    public DamageSource d() {
        this.w = true;
        return this;
    }

    public boolean ignoresArmor() {
        return this.p;
    }

    public float f() {
        return this.r;
    }

    public boolean ignoresInvulnerability() {
        return this.q;
    }

    protected DamageSource(String s) {
        this.translationIndex = s;
    }

    public Entity h() {
        return this.getEntity();
    }

    public Entity getEntity() {
        return null;
    }

    protected DamageSource j() {
        this.p = true;
        this.r = 0.0F;
        return this;
    }

    protected DamageSource k() {
        this.q = true;
        return this;
    }

    protected DamageSource l() {
        this.s = true;
        return this;
    }

    public String getLocalizedDeathMessage(EntityLiving entityliving) {
        EntityLiving entityliving1 = entityliving.bN();
        String s = "death.attack." + this.translationIndex;
        String s1 = s + ".player";

        return entityliving1 != null && LocaleI18n.b(s1) ? LocaleI18n.get(s1, new Object[] { entityliving.getScoreboardDisplayName(), entityliving1.getScoreboardDisplayName()}) : LocaleI18n.get(s, new Object[] { entityliving.getScoreboardDisplayName()});
    }

    public boolean m() {
        return this.s;
    }

    public String n() {
        return this.translationIndex;
    }

    public DamageSource o() {
        this.u = true;
        return this;
    }

    public boolean p() {
        return this.u;
    }

    public boolean q() {
        return this.v;
    }

    public DamageSource r() {
        this.v = true;
        return this;
    }
}

package net.minecraft.server;

public class DamageSource {

    public static DamageSource FIRE = (new DamageSource("inFire")).n();
    public static DamageSource BURN = (new DamageSource("onFire")).k().n();
    public static DamageSource LAVA = (new DamageSource("lava")).n();
    public static DamageSource STUCK = (new DamageSource("inWall")).k();
    public static DamageSource DROWN = (new DamageSource("drown")).k();
    public static DamageSource STARVE = (new DamageSource("starve")).k().m();
    public static DamageSource CACTUS = new DamageSource("cactus");
    public static DamageSource FALL = (new DamageSource("fall")).k();
    public static DamageSource OUT_OF_WORLD = (new DamageSource("outOfWorld")).k().l();
    public static DamageSource GENERIC = (new DamageSource("generic")).k();
    public static DamageSource MAGIC = (new DamageSource("magic")).k().t();
    public static DamageSource WITHER = (new DamageSource("wither")).k();
    public static DamageSource ANVIL = new DamageSource("anvil");
    public static DamageSource FALLING_BLOCK = new DamageSource("fallingBlock");
    private boolean p;
    private boolean q;
    private boolean r;
    private float s = 0.3F;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
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
        return entity == null ? (new EntityDamageSourceIndirect("onFire", entityfireball, entityfireball)).n().b() : (new EntityDamageSourceIndirect("fireball", entityfireball, entity)).n().b();
    }

    public static DamageSource projectile(Entity entity, Entity entity1) {
        return (new EntityDamageSourceIndirect("thrown", entity, entity1)).b();
    }

    public static DamageSource b(Entity entity, Entity entity1) {
        return (new EntityDamageSourceIndirect("indirectMagic", entity, entity1)).k().t();
    }

    public static DamageSource a(Entity entity) {
        return (new EntityDamageSource("thorns", entity)).t();
    }

    public static DamageSource explosion(Explosion explosion) {
        return explosion != null && explosion.c() != null ? (new EntityDamageSource("explosion.player", explosion.c())).q().d() : (new DamageSource("explosion")).q().d();
    }

    public boolean a() {
        return this.u;
    }

    public DamageSource b() {
        this.u = true;
        return this;
    }

    public boolean c() {
        return this.x;
    }

    public DamageSource d() {
        this.x = true;
        return this;
    }

    public boolean ignoresArmor() {
        return this.p;
    }

    public float f() {
        return this.s;
    }

    public boolean ignoresInvulnerability() {
        return this.q;
    }

    public boolean h() {
        return this.r;
    }

    protected DamageSource(String s) {
        this.translationIndex = s;
    }

    public Entity i() {
        return this.getEntity();
    }

    public Entity getEntity() {
        return null;
    }

    protected DamageSource k() {
        this.p = true;
        this.s = 0.0F;
        return this;
    }

    protected DamageSource l() {
        this.q = true;
        return this;
    }

    protected DamageSource m() {
        this.r = true;
        this.s = 0.0F;
        return this;
    }

    protected DamageSource n() {
        this.t = true;
        return this;
    }

    public IChatBaseComponent getLocalizedDeathMessage(EntityLiving entityliving) {
        EntityLiving entityliving1 = entityliving.aX();
        String s = "death.attack." + this.translationIndex;
        String s1 = s + ".player";

        return entityliving1 != null && LocaleI18n.c(s1) ? new ChatMessage(s1, new Object[] { entityliving.getScoreboardDisplayName(), entityliving1.getScoreboardDisplayName()}) : new ChatMessage(s, new Object[] { entityliving.getScoreboardDisplayName()});
    }

    public boolean o() {
        return this.t;
    }

    public String p() {
        return this.translationIndex;
    }

    public DamageSource q() {
        this.v = true;
        return this;
    }

    public boolean r() {
        return this.v;
    }

    public boolean s() {
        return this.w;
    }

    public DamageSource t() {
        this.w = true;
        return this;
    }
}

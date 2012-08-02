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
    public static DamageSource EXPLOSION = (new DamageSource("explosion")).m();
    public static DamageSource EXPLOSION2 = new DamageSource("explosion");
    public static DamageSource MAGIC = (new DamageSource("magic")).h();
    private boolean o = false;
    private boolean p = false;
    private float q = 0.3F;
    private boolean r;
    private boolean s;
    private boolean t;
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
        return entity == null ? (new EntityDamageSourceIndirect("onFire", entityfireball, entityfireball)).j().b() : (new EntityDamageSourceIndirect("fireball", entityfireball, entity)).j().b();
    }

    public static DamageSource projectile(Entity entity, Entity entity1) {
        return (new EntityDamageSourceIndirect("thrown", entity, entity1)).b();
    }

    public static DamageSource b(Entity entity, Entity entity1) {
        return (new EntityDamageSourceIndirect("indirectMagic", entity, entity1)).h();
    }

    public boolean a() {
        return this.s;
    }

    public DamageSource b() {
        this.s = true;
        return this;
    }

    public boolean ignoresArmor() {
        return this.o;
    }

    public float d() {
        return this.q;
    }

    public boolean ignoresInvulnerability() {
        return this.p;
    }

    protected DamageSource(String s) {
        this.translationIndex = s;
    }

    public Entity f() {
        return this.getEntity();
    }

    public Entity getEntity() {
        return null;
    }

    protected DamageSource h() {
        this.o = true;
        this.q = 0.0F;
        return this;
    }

    protected DamageSource i() {
        this.p = true;
        return this;
    }

    protected DamageSource j() {
        this.r = true;
        return this;
    }

    public String getLocalizedDeathMessage(EntityHuman entityhuman) {
        return LocaleI18n.get("death." + this.translationIndex, new Object[] { entityhuman.name});
    }

    public boolean k() {
        return this.r;
    }

    public String l() {
        return this.translationIndex;
    }

    public DamageSource m() {
        this.t = true;
        return this;
    }

    public boolean n() {
        return this.t;
    }
}

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
    private boolean n = false;
    private boolean o = false;
    private float p = 0.3F;
    private boolean q;
    private boolean r;
    public String m;

    public static DamageSource mobAttack(EntityLiving entityliving) {
        return new EntityDamageSource("mob", entityliving);
    }

    public static DamageSource playerAttack(EntityHuman entityhuman) {
        return new EntityDamageSource("player", entityhuman);
    }

    public static DamageSource arrow(EntityArrow entityarrow, Entity entity) {
        return (new EntityDamageSourceIndirect("arrow", entityarrow, entity)).c();
    }

    public static DamageSource fireball(EntityFireball entityfireball, Entity entity) {
        return (new EntityDamageSourceIndirect("fireball", entityfireball, entity)).j().c();
    }

    public static DamageSource projectile(Entity entity, Entity entity1) {
        return (new EntityDamageSourceIndirect("thrown", entity, entity1)).c();
    }

    public static DamageSource b(Entity entity, Entity entity1) {
        return (new EntityDamageSourceIndirect("indirectMagic", entity, entity1)).h();
    }

    public boolean b() {
        return this.r;
    }

    public DamageSource c() {
        this.r = true;
        return this;
    }

    public boolean ignoresArmor() {
        return this.n;
    }

    public float e() {
        return this.p;
    }

    public boolean ignoresInvulnerability() {
        return this.o;
    }

    protected DamageSource(String s) {
        this.m = s;
    }

    public Entity g() {
        return this.getEntity();
    }

    public Entity getEntity() {
        return null;
    }

    protected DamageSource h() {
        this.n = true;
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

    public String a(EntityHuman entityhuman) {
        return LocaleI18n.a("death." + this.m, new Object[] { entityhuman.name});
    }

    public boolean k() {
        return this.q;
    }

    public String l() {
        return this.m;
    }
}

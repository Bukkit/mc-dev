package net.minecraft.server;

public class DamageSource {

    public static DamageSource a = new DamageSource("inFire");
    public static DamageSource b = (new DamageSource("onFire")).f();
    public static DamageSource c = new DamageSource("lava");
    public static DamageSource d = (new DamageSource("inWall")).f();
    public static DamageSource e = (new DamageSource("drown")).f();
    public static DamageSource f = (new DamageSource("starve")).f();
    public static DamageSource g = new DamageSource("cactus");
    public static DamageSource h = new DamageSource("fall");
    public static DamageSource i = (new DamageSource("outOfWorld")).f().g();
    public static DamageSource j = (new DamageSource("generic")).f();
    public static DamageSource k = new DamageSource("explosion");
    public static DamageSource l = (new DamageSource("magic")).f();
    private boolean n = false;
    private boolean o = false;
    private float p = 0.3F;
    public String m;

    public static DamageSource a(EntityLiving entityliving) {
        return new EntityDamageSource("mob", entityliving);
    }

    public static DamageSource b(EntityHuman entityhuman) {
        return new EntityDamageSource("player", entityhuman);
    }

    public static DamageSource a(EntityArrow entityarrow, Entity entity) {
        return new EntityDamageSourceIndirect("arrow", entityarrow, entity);
    }

    public static DamageSource a(EntityFireball entityfireball, Entity entity) {
        return new EntityDamageSourceIndirect("fireball", entityfireball, entity);
    }

    public static DamageSource a(Entity entity, Entity entity1) {
        return new EntityDamageSourceIndirect("thrown", entity, entity1);
    }

    public boolean b() {
        return this.n;
    }

    public float c() {
        return this.p;
    }

    public boolean d() {
        return this.o;
    }

    protected DamageSource(String s) {
        this.m = s;
    }

    public Entity e() {
        return this.a();
    }

    public Entity a() {
        return null;
    }

    private DamageSource f() {
        this.n = true;
        this.p = 0.0F;
        return this;
    }

    private DamageSource g() {
        this.o = true;
        return this;
    }

    public String a(EntityHuman entityhuman) {
        return StatisticCollector.a("death." + this.m, new Object[] { entityhuman.name});
    }
}

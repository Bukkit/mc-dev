package net.minecraft.server;

public class EntityDamageSource extends DamageSource {

    private Entity n;

    public EntityDamageSource(String s, Entity entity) {
        super(s);
        this.n = entity;
    }

    public Entity a() {
        return this.n;
    }

    public String a(EntityHuman entityhuman) {
        return StatisticCollector.a("death." + this.m, new Object[] { entityhuman.name, this.n.Y()});
    }
}

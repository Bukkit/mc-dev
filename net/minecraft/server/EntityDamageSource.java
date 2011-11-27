package net.minecraft.server;

public class EntityDamageSource extends DamageSource {

    private Entity n;

    public EntityDamageSource(String s, Entity entity) {
        super(s);
        this.n = entity;
    }

    public Entity getEntity() {
        return this.n;
    }

    public String a(EntityHuman entityhuman) {
        return LocaleI18n.a("death." + this.m, new Object[] { entityhuman.name, this.n.ad()});
    }
}

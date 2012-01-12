package net.minecraft.server;

public class EntityDamageSource extends DamageSource {

    protected Entity a;

    public EntityDamageSource(String s, Entity entity) {
        super(s);
        this.a = entity;
    }

    public Entity getEntity() {
        return this.a;
    }

    public String a(EntityHuman entityhuman) {
        return LocaleI18n.a("death." + this.n, new Object[] { entityhuman.name, this.a.ad()});
    }
}

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

    public String getLocalizedDeathMessage(EntityHuman entityhuman) {
        return LocaleI18n.get("death." + this.translationIndex, new Object[] { entityhuman.name, this.a.getLocalizedName()});
    }
}

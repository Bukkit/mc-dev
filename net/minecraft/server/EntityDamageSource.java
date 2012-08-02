package net.minecraft.server;

public class EntityDamageSource extends DamageSource {

    protected Entity o;

    public EntityDamageSource(String s, Entity entity) {
        super(s);
        this.o = entity;
    }

    public Entity getEntity() {
        return this.o;
    }

    public String getLocalizedDeathMessage(EntityHuman entityhuman) {
        return LocaleI18n.get("death." + this.translationIndex, new Object[] { entityhuman.name, this.o.getLocalizedName()});
    }

    public boolean n() {
        return this.o != null && this.o instanceof EntityLiving && !(this.o instanceof EntityHuman);
    }
}

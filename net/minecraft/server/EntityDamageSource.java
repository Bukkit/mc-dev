package net.minecraft.server;

public class EntityDamageSource extends DamageSource {

    protected Entity r;

    public EntityDamageSource(String s, Entity entity) {
        super(s);
        this.r = entity;
    }

    public Entity getEntity() {
        return this.r;
    }

    public String getLocalizedDeathMessage(EntityHuman entityhuman) {
        return LocaleI18n.get("death." + this.translationIndex, new Object[] { entityhuman.name, this.r.getLocalizedName()});
    }

    public boolean n() {
        return this.r != null && this.r instanceof EntityLiving && !(this.r instanceof EntityHuman);
    }
}

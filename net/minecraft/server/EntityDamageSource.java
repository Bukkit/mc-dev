package net.minecraft.server;

public class EntityDamageSource extends DamageSource {

    protected Entity p;

    public EntityDamageSource(String s, Entity entity) {
        super(s);
        this.p = entity;
    }

    public Entity getEntity() {
        return this.p;
    }

    public IChatBaseComponent getLocalizedDeathMessage(EntityLiving entityliving) {
        ItemStack itemstack = this.p instanceof EntityLiving ? ((EntityLiving) this.p).be() : null;
        String s = "death.attack." + this.translationIndex;
        String s1 = s + ".item";

        return itemstack != null && itemstack.hasName() && LocaleI18n.c(s1) ? new ChatMessage(s1, new Object[] { entityliving.getScoreboardDisplayName(), this.p.getScoreboardDisplayName(), itemstack.E()}) : new ChatMessage(s, new Object[] { entityliving.getScoreboardDisplayName(), this.p.getScoreboardDisplayName()});
    }

    public boolean r() {
        return this.p != null && this.p instanceof EntityLiving && !(this.p instanceof EntityHuman);
    }
}

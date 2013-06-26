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

    public ChatMessage getLocalizedDeathMessage(EntityLiving entityliving) {
        ItemStack itemstack = this.p instanceof EntityLiving ? ((EntityLiving) this.p).aV() : null;
        String s = "death.attack." + this.translationIndex;
        String s1 = s + ".item";

        return itemstack != null && itemstack.hasName() && LocaleI18n.b(s1) ? ChatMessage.b(s1, new Object[] { entityliving.getScoreboardDisplayName(), this.p.getScoreboardDisplayName(), itemstack.getName()}) : ChatMessage.b(s, new Object[] { entityliving.getScoreboardDisplayName(), this.p.getScoreboardDisplayName()});
    }

    public boolean p() {
        return this.p != null && this.p instanceof EntityLiving && !(this.p instanceof EntityHuman);
    }
}

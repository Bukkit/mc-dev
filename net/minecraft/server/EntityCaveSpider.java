package net.minecraft.server;

public class EntityCaveSpider extends EntitySpider {

    public EntityCaveSpider(World world) {
        super(world);
        this.a(0.7F, 0.5F);
    }

    protected void aD() {
        super.aD();
        this.getAttributeInstance(GenericAttributes.a).setValue(12.0D);
    }

    public boolean m(Entity entity) {
        if (super.m(entity)) {
            if (entity instanceof EntityLiving) {
                byte b0 = 0;

                if (this.world.difficulty == EnumDifficulty.NORMAL) {
                    b0 = 7;
                } else if (this.world.difficulty == EnumDifficulty.HARD) {
                    b0 = 15;
                }

                if (b0 > 0) {
                    ((EntityLiving) entity).addEffect(new MobEffect(MobEffectList.POISON.id, b0 * 20, 0));
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public GroupDataEntity a(GroupDataEntity groupdataentity) {
        return groupdataentity;
    }
}

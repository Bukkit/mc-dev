package net.minecraft.server;

public class EntitySelectorEquipable implements IEntitySelector {

    private final ItemStack d;

    public EntitySelectorEquipable(ItemStack itemstack) {
        this.d = itemstack;
    }

    public boolean a(Entity entity) {
        if (!entity.isAlive()) {
            return false;
        } else if (!(entity instanceof EntityLiving)) {
            return false;
        } else {
            EntityLiving entityliving = (EntityLiving) entity;

            return entityliving.getEquipment(EntityInsentient.b(this.d)) != null ? false : (entityliving instanceof EntityInsentient ? ((EntityInsentient) entityliving).bJ() : entityliving instanceof EntityHuman);
        }
    }
}

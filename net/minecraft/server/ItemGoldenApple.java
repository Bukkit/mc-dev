package net.minecraft.server;

public class ItemGoldenApple extends ItemFood {

    public ItemGoldenApple(int i, float f, boolean flag) {
        super(i, f, flag);
        this.a(true);
    }

    public EnumItemRarity f(ItemStack itemstack) {
        return itemstack.getData() == 0 ? EnumItemRarity.RARE : EnumItemRarity.EPIC;
    }

    protected void c(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (!world.isStatic) {
            entityhuman.addEffect(new MobEffect(MobEffectList.ABSORPTION.id, 2400, 0));
        }

        if (itemstack.getData() > 0) {
            if (!world.isStatic) {
                entityhuman.addEffect(new MobEffect(MobEffectList.REGENERATION.id, 600, 4));
                entityhuman.addEffect(new MobEffect(MobEffectList.RESISTANCE.id, 6000, 0));
                entityhuman.addEffect(new MobEffect(MobEffectList.FIRE_RESISTANCE.id, 6000, 0));
            }
        } else {
            super.c(itemstack, world, entityhuman);
        }
    }
}

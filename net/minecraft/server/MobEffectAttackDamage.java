package net.minecraft.server;

public class MobEffectAttackDamage extends MobEffectList {

    protected MobEffectAttackDamage(int i, boolean flag, int j) {
        super(i, flag, j);
    }

    public double a(int i, AttributeModifier attributemodifier) {
        return this.id == MobEffectList.WEAKNESS.id ? (double) (-0.5F * (float) (i + 1)) : 1.3D * (double) (i + 1);
    }
}

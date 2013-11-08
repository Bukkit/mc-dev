package net.minecraft.server;

final class EnchantmentModifierThorns implements EnchantmentModifier {

    public EntityLiving a;
    public Entity b;

    private EnchantmentModifierThorns() {}

    public void a(Enchantment enchantment, int i) {
        enchantment.b(this.a, this.b, i);
    }

    EnchantmentModifierThorns(EmptyClass emptyclass) {
        this();
    }
}

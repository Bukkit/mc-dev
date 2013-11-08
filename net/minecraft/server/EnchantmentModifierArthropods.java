package net.minecraft.server;

final class EnchantmentModifierArthropods implements EnchantmentModifier {

    public EntityLiving a;
    public Entity b;

    private EnchantmentModifierArthropods() {}

    public void a(Enchantment enchantment, int i) {
        enchantment.a(this.a, this.b, i);
    }

    EnchantmentModifierArthropods(EmptyClass emptyclass) {
        this();
    }
}

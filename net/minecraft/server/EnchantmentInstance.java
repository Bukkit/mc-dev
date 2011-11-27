package net.minecraft.server;

public class EnchantmentInstance extends WeightedRandomChoice {

    public final Enchantment a;
    public final int b;

    public EnchantmentInstance(Enchantment enchantment, int i) {
        super(enchantment.getRandomWeight());
        this.a = enchantment;
        this.b = i;
    }
}

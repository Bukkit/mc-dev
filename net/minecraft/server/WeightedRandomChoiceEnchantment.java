package net.minecraft.server;

public class WeightedRandomChoiceEnchantment extends WeightedRandomChoice {

    public final Enchantment a;
    public final int b;

    public WeightedRandomChoiceEnchantment(Enchantment enchantment, int i) {
        super(enchantment.getRandomWeight());
        this.a = enchantment;
        this.b = i;
    }
}

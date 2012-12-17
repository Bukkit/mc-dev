package net.minecraft.server;

public class EnchantmentInstance extends WeightedRandomChoice {

    public final Enchantment enchantment;
    public final int level;

    public EnchantmentInstance(Enchantment enchantment, int i) {
        super(enchantment.getRandomWeight());
        this.enchantment = enchantment;
        this.level = i;
    }

    public EnchantmentInstance(int i, int j) {
        this(Enchantment.byId[i], j);
    }
}

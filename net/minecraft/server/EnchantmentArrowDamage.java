package net.minecraft.server;

public class EnchantmentArrowDamage extends Enchantment {

    public EnchantmentArrowDamage(int i, int j) {
        super(i, j, EnchantmentSlotType.BOW);
        this.b("arrowDamage");
    }

    public int a(int i) {
        return 1 + (i - 1) * 10;
    }

    public int b(int i) {
        return this.a(i) + 15;
    }

    public int getMaxLevel() {
        return 5;
    }
}

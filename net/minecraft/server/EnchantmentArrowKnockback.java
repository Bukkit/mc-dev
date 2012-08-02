package net.minecraft.server;

public class EnchantmentArrowKnockback extends Enchantment {

    public EnchantmentArrowKnockback(int i, int j) {
        super(i, j, EnchantmentSlotType.BOW);
        this.b("arrowKnockback");
    }

    public int a(int i) {
        return 12 + (i - 1) * 20;
    }

    public int b(int i) {
        return this.a(i) + 25;
    }

    public int getMaxLevel() {
        return 2;
    }
}

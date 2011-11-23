package net.minecraft.server;

public class EnchantmentKnockback extends Enchantment {

    protected EnchantmentKnockback(int i, int j) {
        super(i, j, EnchantmentSlotType.WEAPON);
        this.a("knockback");
    }

    public int a(int i) {
        return 5 + 20 * (i - 1);
    }

    public int b(int i) {
        return super.a(i) + 50;
    }

    public int getMaxLevel() {
        return 2;
    }
}

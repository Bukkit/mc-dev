package net.minecraft.server;

public class EnchantmentDigging extends Enchantment {

    protected EnchantmentDigging(int i, int j) {
        super(i, j, EnchantmentSlotType.DIGGER);
        this.a("digging");
    }

    public int a(int i) {
        return 1 + 15 * (i - 1);
    }

    public int b(int i) {
        return super.a(i) + 50;
    }

    public int getMaxLevel() {
        return 5;
    }
}

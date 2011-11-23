package net.minecraft.server;

public class EnchantmentOxygen extends Enchantment {

    public EnchantmentOxygen(int i, int j) {
        super(i, j, EnchantmentSlotType.ARMOR_HEAD);
        this.a("oxygen");
    }

    public int a(int i) {
        return 10 * i;
    }

    public int b(int i) {
        return this.a(i) + 30;
    }

    public int getMaxLevel() {
        return 3;
    }
}

package net.minecraft.server;

public class EnchantmentLure extends Enchantment {

    protected EnchantmentLure(int i, int j, EnchantmentSlotType enchantmentslottype) {
        super(i, j, enchantmentslottype);
        this.b("fishingSpeed");
    }

    public int a(int i) {
        return 15 + (i - 1) * 9;
    }

    public int b(int i) {
        return super.a(i) + 50;
    }

    public int getMaxLevel() {
        return 3;
    }
}

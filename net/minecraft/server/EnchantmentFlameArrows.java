package net.minecraft.server;

public class EnchantmentFlameArrows extends Enchantment {

    public EnchantmentFlameArrows(int i, int j) {
        super(i, j, EnchantmentSlotType.BOW);
        this.b("arrowFire");
    }

    public int a(int i) {
        return 20;
    }

    public int b(int i) {
        return 50;
    }

    public int getMaxLevel() {
        return 1;
    }
}

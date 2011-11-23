package net.minecraft.server;

public class EnchantmentWaterWorker extends Enchantment {

    public EnchantmentWaterWorker(int i, int j) {
        super(i, j, EnchantmentSlotType.ARMOR_HEAD);
        this.a("waterWorker");
    }

    public int a(int i) {
        return 1;
    }

    public int b(int i) {
        return this.a(i) + 40;
    }

    public int getMaxLevel() {
        return 1;
    }
}

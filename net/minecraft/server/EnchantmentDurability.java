package net.minecraft.server;

public class EnchantmentDurability extends Enchantment {

    protected EnchantmentDurability(int i, int j) {
        super(i, j, EnchantmentSlotType.DIGGER);
        this.b("durability");
    }

    public int a(int i) {
        return 5 + (i - 1) * 8;
    }

    public int b(int i) {
        return super.a(i) + 50;
    }

    public int getMaxLevel() {
        return 3;
    }
}

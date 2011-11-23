package net.minecraft.server;

public class EnchantmentSilkTouch extends Enchantment {

    protected EnchantmentSilkTouch(int i, int j) {
        super(i, j, EnchantmentSlotType.DIGGER);
        this.a("untouching");
    }

    public int a(int i) {
        return 25;
    }

    public int b(int i) {
        return super.a(i) + 50;
    }

    public int getMaxLevel() {
        return 1;
    }

    public boolean a(Enchantment enchantment) {
        return super.a(enchantment) && enchantment.id != LOOT_BONUS_BLOCKS.id;
    }
}

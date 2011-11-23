package net.minecraft.server;

public class EnchantmentLootBonus extends Enchantment {

    protected EnchantmentLootBonus(int i, int j, EnchantmentSlotType enchantmentslottype) {
        super(i, j, enchantmentslottype);
        this.a("lootBonus");
        if (enchantmentslottype == EnchantmentSlotType.DIGGER) {
            this.a("lootBonusDigger");
        }
    }

    public int a(int i) {
        return 20 + (i - 1) * 12;
    }

    public int b(int i) {
        return super.a(i) + 50;
    }

    public int getMaxLevel() {
        return 3;
    }

    public boolean a(Enchantment enchantment) {
        return super.a(enchantment) && enchantment.id != SILK_TOUCH.id;
    }
}

package net.minecraft.server;

public class EnchantmentLootBonus extends Enchantment {

    protected EnchantmentLootBonus(int i, int j, EnchantmentSlotType enchantmentslottype) {
        super(i, j, enchantmentslottype);
        if (enchantmentslottype == EnchantmentSlotType.DIGGER) {
            this.b("lootBonusDigger");
        } else if (enchantmentslottype == EnchantmentSlotType.FISHING_ROD) {
            this.b("lootBonusFishing");
        } else {
            this.b("lootBonus");
        }
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

    public boolean a(Enchantment enchantment) {
        return super.a(enchantment) && enchantment.id != SILK_TOUCH.id;
    }
}

package net.minecraft.server;

import java.util.Random;

public class EnchantmentDurability extends Enchantment {

    protected EnchantmentDurability(int i, int j) {
        super(i, j, EnchantmentSlotType.BREAKABLE);
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

    public boolean canEnchant(ItemStack itemstack) {
        return itemstack.g() ? true : super.canEnchant(itemstack);
    }

    public static boolean a(ItemStack itemstack, int i, Random random) {
        return itemstack.getItem() instanceof ItemArmor && random.nextFloat() < 0.6F ? false : random.nextInt(i + 1) > 0;
    }
}

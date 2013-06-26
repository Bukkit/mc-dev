package net.minecraft.server;

public class EnchantmentWeaponDamage extends Enchantment {

    private static final String[] C = new String[] { "all", "undead", "arthropods"};
    private static final int[] D = new int[] { 1, 5, 5};
    private static final int[] E = new int[] { 11, 8, 8};
    private static final int[] F = new int[] { 20, 20, 20};
    public final int a;

    public EnchantmentWeaponDamage(int i, int j, int k) {
        super(i, j, EnchantmentSlotType.WEAPON);
        this.a = k;
    }

    public int a(int i) {
        return D[this.a] + (i - 1) * E[this.a];
    }

    public int b(int i) {
        return this.a(i) + F[this.a];
    }

    public int getMaxLevel() {
        return 5;
    }

    public float a(int i, EntityLiving entityliving) {
        return this.a == 0 ? (float) i * 1.25F : (this.a == 1 && entityliving.getMonsterType() == EnumMonsterType.UNDEAD ? (float) i * 2.5F : (this.a == 2 && entityliving.getMonsterType() == EnumMonsterType.ARTHROPOD ? (float) i * 2.5F : 0.0F));
    }

    public String a() {
        return "enchantment.damage." + C[this.a];
    }

    public boolean a(Enchantment enchantment) {
        return !(enchantment instanceof EnchantmentWeaponDamage);
    }

    public boolean canEnchant(ItemStack itemstack) {
        return itemstack.getItem() instanceof ItemAxe ? true : super.canEnchant(itemstack);
    }
}

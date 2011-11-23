package net.minecraft.server;

public class EnchantmentProtection extends Enchantment {

    private static final String[] w = new String[] { "all", "fire", "fall", "explosion", "projectile"};
    private static final int[] x = new int[] { 1, 10, 5, 5, 3};
    private static final int[] y = new int[] { 16, 8, 6, 8, 6};
    private static final int[] z = new int[] { 20, 12, 10, 12, 15};
    public final int a;

    public EnchantmentProtection(int i, int j, int k) {
        super(i, j, EnchantmentSlotType.ARMOR);
        this.a = k;
        if (k == 2) {
            this.slot = EnchantmentSlotType.ARMOR_FEET;
        }
    }

    public int a(int i) {
        return x[this.a] + (i - 1) * y[this.a];
    }

    public int b(int i) {
        return this.a(i) + z[this.a];
    }

    public int getMaxLevel() {
        return 4;
    }

    public int a(int i, DamageSource damagesource) {
        if (damagesource.ignoresInvulnerability()) {
            return 0;
        } else {
            int j = (6 + i * i) / 2;

            return this.a == 0 ? j : (this.a == 1 && damagesource.k() ? j : (this.a == 2 && damagesource == DamageSource.FALL ? j * 2 : (this.a == 3 && damagesource == DamageSource.EXPLOSION ? j : (this.a == 4 && damagesource.b() ? j : 0))));
        }
    }

    public boolean a(Enchantment enchantment) {
        if (enchantment instanceof EnchantmentProtection) {
            EnchantmentProtection enchantmentprotection = (EnchantmentProtection) enchantment;

            return enchantmentprotection.a == this.a ? false : this.a == 2 || enchantmentprotection.a == 2;
        } else {
            return super.a(enchantment);
        }
    }
}

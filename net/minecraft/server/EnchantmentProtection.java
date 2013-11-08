package net.minecraft.server;

public class EnchantmentProtection extends Enchantment {

    private static final String[] E = new String[] { "all", "fire", "fall", "explosion", "projectile"};
    private static final int[] F = new int[] { 1, 10, 5, 5, 3};
    private static final int[] G = new int[] { 11, 8, 6, 8, 6};
    private static final int[] H = new int[] { 20, 12, 10, 12, 15};
    public final int a;

    public EnchantmentProtection(int i, int j, int k) {
        super(i, j, EnchantmentSlotType.ARMOR);
        this.a = k;
        if (k == 2) {
            this.slot = EnchantmentSlotType.ARMOR_FEET;
        }
    }

    public int a(int i) {
        return F[this.a] + (i - 1) * G[this.a];
    }

    public int b(int i) {
        return this.a(i) + H[this.a];
    }

    public int getMaxLevel() {
        return 4;
    }

    public int a(int i, DamageSource damagesource) {
        if (damagesource.ignoresInvulnerability()) {
            return 0;
        } else {
            float f = (float) (6 + i * i) / 3.0F;

            return this.a == 0 ? MathHelper.d(f * 0.75F) : (this.a == 1 && damagesource.o() ? MathHelper.d(f * 1.25F) : (this.a == 2 && damagesource == DamageSource.FALL ? MathHelper.d(f * 2.5F) : (this.a == 3 && damagesource.c() ? MathHelper.d(f * 1.5F) : (this.a == 4 && damagesource.a() ? MathHelper.d(f * 1.5F) : 0))));
        }
    }

    public String a() {
        return "enchantment.protect." + E[this.a];
    }

    public boolean a(Enchantment enchantment) {
        if (enchantment instanceof EnchantmentProtection) {
            EnchantmentProtection enchantmentprotection = (EnchantmentProtection) enchantment;

            return enchantmentprotection.a == this.a ? false : this.a == 2 || enchantmentprotection.a == 2;
        } else {
            return super.a(enchantment);
        }
    }

    public static int a(Entity entity, int i) {
        int j = EnchantmentManager.getEnchantmentLevel(Enchantment.PROTECTION_FIRE.id, entity.getEquipment());

        if (j > 0) {
            i -= MathHelper.d((float) i * (float) j * 0.15F);
        }

        return i;
    }

    public static double a(Entity entity, double d0) {
        int i = EnchantmentManager.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS.id, entity.getEquipment());

        if (i > 0) {
            d0 -= (double) MathHelper.floor(d0 * (double) ((float) i * 0.15F));
        }

        return d0;
    }
}

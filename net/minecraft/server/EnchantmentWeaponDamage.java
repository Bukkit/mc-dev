package net.minecraft.server;

public class EnchantmentWeaponDamage extends Enchantment {

    private static final String[] A = new String[] { "all", "undead", "arthropods"};
    private static final int[] B = new int[] { 1, 5, 5};
    private static final int[] C = new int[] { 16, 8, 8};
    private static final int[] D = new int[] { 20, 20, 20};
    public final int a;

    public EnchantmentWeaponDamage(int i, int j, int k) {
        super(i, j, EnchantmentSlotType.WEAPON);
        this.a = k;
    }

    public int a(int i) {
        return B[this.a] + (i - 1) * C[this.a];
    }

    public int b(int i) {
        return this.a(i) + D[this.a];
    }

    public int getMaxLevel() {
        return 5;
    }

    public int a(int i, EntityLiving entityliving) {
        return this.a == 0 ? i * 3 : (this.a == 1 && entityliving.getMonsterType() == MonsterType.UNDEAD ? i * 4 : (this.a == 2 && entityliving.getMonsterType() == MonsterType.ARTHROPOD ? i * 4 : 0));
    }

    public boolean a(Enchantment enchantment) {
        return !(enchantment instanceof EnchantmentWeaponDamage);
    }
}

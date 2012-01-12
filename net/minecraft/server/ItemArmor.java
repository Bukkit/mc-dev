package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] bT = new int[] { 11, 16, 15, 13};
    public final int a;
    public final int b;
    public final int bS;
    private final EnumArmorMaterial bU;

    public ItemArmor(int i, EnumArmorMaterial enumarmormaterial, int j, int k) {
        super(i);
        this.bU = enumarmormaterial;
        this.a = k;
        this.bS = j;
        this.b = enumarmormaterial.b(k);
        this.f(enumarmormaterial.a(k));
        this.maxStackSize = 1;
    }

    public int c() {
        return this.bU.a();
    }

    static int[] o() {
        return bT;
    }
}

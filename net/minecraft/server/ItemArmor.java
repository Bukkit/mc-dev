package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] bS = new int[] { 11, 16, 15, 13};
    public final int a;
    public final int b;
    public final int bR;
    private final EnumArmorMaterial bT;

    public ItemArmor(int i, EnumArmorMaterial enumarmormaterial, int j, int k) {
        super(i);
        this.bT = enumarmormaterial;
        this.a = k;
        this.bR = j;
        this.b = enumarmormaterial.b(k);
        this.f(enumarmormaterial.a(k));
        this.maxStackSize = 1;
    }

    public int c() {
        return this.bT.a();
    }

    static int[] n() {
        return bS;
    }
}

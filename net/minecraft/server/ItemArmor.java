package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] bV = new int[] { 11, 16, 15, 13};
    public final int a;
    public final int b;
    public final int bU;
    private final EnumArmorMaterial bW;

    public ItemArmor(int i, EnumArmorMaterial enumarmormaterial, int j, int k) {
        super(i);
        this.bW = enumarmormaterial;
        this.a = k;
        this.bU = j;
        this.b = enumarmormaterial.b(k);
        this.setMaxDurability(enumarmormaterial.a(k));
        this.maxStackSize = 1;
    }

    public int c() {
        return this.bW.a();
    }

    static int[] o() {
        return bV;
    }
}

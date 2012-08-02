package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] bY = new int[] { 11, 16, 15, 13};
    public final int a;
    public final int b;
    public final int c;
    private final EnumArmorMaterial bZ;

    public ItemArmor(int i, EnumArmorMaterial enumarmormaterial, int j, int k) {
        super(i);
        this.bZ = enumarmormaterial;
        this.a = k;
        this.c = j;
        this.b = enumarmormaterial.b(k);
        this.setMaxDurability(enumarmormaterial.a(k));
        this.maxStackSize = 1;
        this.a(CreativeModeTab.j);
    }

    public int b() {
        return this.bZ.a();
    }

    static int[] c() {
        return bY;
    }
}

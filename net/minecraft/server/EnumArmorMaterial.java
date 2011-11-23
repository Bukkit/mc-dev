package net.minecraft.server;

public enum EnumArmorMaterial {

    CLOTH("CLOTH", 0, 5, new int[] { 1, 3, 2, 1}, 15), IRON("CHAIN", 1, 15, new int[] { 2, 5, 4, 1}, 12), CHAIN("IRON", 2, 15, new int[] { 2, 6, 5, 2}, 9), GOLD("GOLD", 3, 7, new int[] { 2, 5, 3, 1}, 25), DIAMOND("DIAMOND", 4, 33, new int[] { 3, 8, 6, 3}, 10);
    private int f;
    private int[] g;
    private int h;

    private static final EnumArmorMaterial[] i = new EnumArmorMaterial[] { CLOTH, IRON, CHAIN, GOLD, DIAMOND};

    private EnumArmorMaterial(String s, int i, int j, int[] aint, int k) {
        this.f = j;
        this.g = aint;
        this.h = k;
    }

    public int a(int i) {
        return ItemArmor.n()[i] * this.f;
    }

    public int b(int i) {
        return this.g[i];
    }

    public int a() {
        return this.h;
    }
}

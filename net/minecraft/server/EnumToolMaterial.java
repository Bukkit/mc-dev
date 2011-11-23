package net.minecraft.server;

public enum EnumToolMaterial {

    WOOD("WOOD", 0, 0, 59, 2.0F, 0, 15), STONE("STONE", 1, 1, 131, 4.0F, 1, 5), IRON("IRON", 2, 2, 250, 6.0F, 2, 14), DIAMOND("EMERALD", 3, 3, 1561, 8.0F, 3, 10), GOLD("GOLD", 4, 0, 32, 12.0F, 0, 22);
    private final int f;
    private final int g;
    private final float h;
    private final int i;
    private final int j;

    private static final EnumToolMaterial[] k = new EnumToolMaterial[] { WOOD, STONE, IRON, DIAMOND, GOLD};

    private EnumToolMaterial(String s, int i, int j, int k, float f, int l, int i1) {
        this.f = j;
        this.g = k;
        this.h = f;
        this.i = l;
        this.j = i1;
    }

    public int a() {
        return this.g;
    }

    public float b() {
        return this.h;
    }

    public int c() {
        return this.i;
    }

    public int d() {
        return this.f;
    }

    public int e() {
        return this.j;
    }
}

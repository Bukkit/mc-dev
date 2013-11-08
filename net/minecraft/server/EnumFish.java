package net.minecraft.server;

import java.util.Map;

import net.minecraft.util.com.google.common.collect.Maps;

public enum EnumFish {

    COD("COD", 0, 0, "cod", 2, 0.1F, 5, 0.6F), SALMON("SALMON", 1, 1, "salmon", 2, 0.1F, 6, 0.8F), CLOWNFISH("CLOWNFISH", 2, 2, "clownfish", 1, 0.1F), PUFFERFISH("PUFFERFISH", 3, 3, "pufferfish", 1, 0.1F);
    private static final Map e = Maps.newHashMap();
    private final int f;
    private final String g;
    private final int j;
    private final float k;
    private final int l;
    private final float m;
    private boolean n = false;
    private static final EnumFish[] o = new EnumFish[] { COD, SALMON, CLOWNFISH, PUFFERFISH};

    private EnumFish(String s, int i, int j, String s1, int k, float f, int l, float f1) {
        this.f = j;
        this.g = s1;
        this.j = k;
        this.k = f;
        this.l = l;
        this.m = f1;
        this.n = true;
    }

    private EnumFish(String s, int i, int j, String s1, int k, float f) {
        this.f = j;
        this.g = s1;
        this.j = k;
        this.k = f;
        this.l = 0;
        this.m = 0.0F;
        this.n = false;
    }

    public int a() {
        return this.f;
    }

    public String b() {
        return this.g;
    }

    public int c() {
        return this.j;
    }

    public float d() {
        return this.k;
    }

    public int e() {
        return this.l;
    }

    public float f() {
        return this.m;
    }

    public boolean i() {
        return this.n;
    }

    public static EnumFish a(int i) {
        EnumFish enumfish = (EnumFish) e.get(Integer.valueOf(i));

        return enumfish == null ? COD : enumfish;
    }

    public static EnumFish a(ItemStack itemstack) {
        return itemstack.getItem() instanceof ItemFish ? a(itemstack.getData()) : COD;
    }

    static {
        EnumFish[] aenumfish = values();
        int i = aenumfish.length;

        for (int j = 0; j < i; ++j) {
            EnumFish enumfish = aenumfish[j];

            e.put(Integer.valueOf(enumfish.a()), enumfish);
        }
    }
}

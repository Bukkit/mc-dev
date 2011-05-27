package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] bm = new int[] { 3, 8, 6, 3};
    private static final int[] bn = new int[] { 11, 16, 15, 13};
    public final int a;
    public final int bj;
    public final int bk;
    public final int bl;

    public ItemArmor(int i, int j, int k, int l) {
        super(i);
        this.a = j;
        this.bj = l;
        this.bl = k;
        this.bk = bm[l];
        this.d(bn[l] * 3 << j);
        this.maxStackSize = 1;
    }
}

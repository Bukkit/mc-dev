package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] bl = new int[] { 3, 8, 6, 3};
    private static final int[] bm = new int[] { 11, 16, 15, 13};
    public final int a;
    public final int bi;
    public final int bj;
    public final int bk;

    public ItemArmor(int i, int j, int k, int l) {
        super(i);
        this.a = j;
        this.bi = l;
        this.bk = k;
        this.bj = bl[l];
        this.durability = bm[l] * 3 << j;
        this.maxStackSize = 1;
    }
}

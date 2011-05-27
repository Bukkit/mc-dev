package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] bj = new int[] { 3, 8, 6, 3};
    private static final int[] bk = new int[] { 11, 16, 15, 13};
    public final int a;
    public final int bg;
    public final int bh;
    public final int bi;

    public ItemArmor(int i, int j, int k, int l) {
        super(i);
        this.a = j;
        this.bg = l;
        this.bi = k;
        this.bh = bj[l];
        this.bc = bk[l] * 3 << j;
        this.bb = 1;
    }
}

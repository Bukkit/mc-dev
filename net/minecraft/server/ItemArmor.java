package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] bw = new int[] { 3, 8, 6, 3};
    private static final int[] bx = new int[] { 11, 16, 15, 13};
    public final int a;
    public final int bt;
    public final int bu;
    public final int bv;

    public ItemArmor(int i, int j, int k, int l) {
        super(i);
        this.a = j;
        this.bt = l;
        this.bv = k;
        this.bu = bw[l];
        this.d(bx[l] * 3 << j);
        this.maxStackSize = 1;
    }
}

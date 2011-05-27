package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] be = new int[] { 3, 8, 6, 3};
    private static final int[] bf = new int[] { 11, 16, 15, 13};
    public final int a;
    public final int bb;
    public final int bc;
    public final int bd;

    public ItemArmor(int i, int j, int k, int l) {
        super(i);
        this.a = j;
        this.bb = l;
        this.bd = k;
        this.bc = be[l];
        this.aY = bf[l] * 3 << j;
        this.aX = 1;
    }
}

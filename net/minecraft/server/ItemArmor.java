package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] ba = new int[] { 3, 8, 6, 3};
    private static final int[] bb = new int[] { 11, 16, 15, 13};
    public final int a;
    public final int aX;
    public final int aY;
    public final int aZ;

    public ItemArmor(int i, int j, int k, int l) {
        super(i);
        this.a = j;
        this.aX = l;
        this.aZ = k;
        this.aY = ba[l];
        this.aU = bb[l] * 3 << j;
        this.aT = 1;
    }
}

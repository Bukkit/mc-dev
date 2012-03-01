package net.minecraft.server;

public class OldNibbleArray {

    public final byte[] a;
    private final int b;
    private final int c;

    public OldNibbleArray(byte[] abyte, int i) {
        this.a = abyte;
        this.b = i;
        this.c = i + 4;
    }

    public int a(int i, int j, int k) {
        int l = i << this.c | k << this.b | j;
        int i1 = l >> 1;
        int j1 = l & 1;

        return j1 == 0 ? this.a[i1] & 15 : this.a[i1] >> 4 & 15;
    }
}

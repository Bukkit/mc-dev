package net.minecraft.server;

public class WorldGenFlatLayerInfo {

    private int a;
    private int b;
    private int c;
    private int d;

    public WorldGenFlatLayerInfo(int i, int j) {
        this.a = 1;
        this.a = i;
        this.b = j;
    }

    public WorldGenFlatLayerInfo(int i, int j, int k) {
        this(i, j);
        this.c = k;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public String toString() {
        String s = Integer.toString(this.b);

        if (this.a > 1) {
            s = this.a + "x" + s;
        }

        if (this.c > 0) {
            s = s + ":" + this.c;
        }

        return s;
    }
}

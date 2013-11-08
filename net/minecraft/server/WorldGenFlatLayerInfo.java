package net.minecraft.server;

public class WorldGenFlatLayerInfo {

    private Block a;
    private int b;
    private int c;
    private int d;

    public WorldGenFlatLayerInfo(int i, Block block) {
        this.b = 1;
        this.b = i;
        this.a = block;
    }

    public WorldGenFlatLayerInfo(int i, Block block, int j) {
        this(i, block);
        this.c = j;
    }

    public int a() {
        return this.b;
    }

    public Block b() {
        return this.a;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public String toString() {
        String s = Integer.toString(Block.b(this.a));

        if (this.b > 1) {
            s = this.b + "x" + s;
        }

        if (this.c > 0) {
            s = s + ":" + this.c;
        }

        return s;
    }
}

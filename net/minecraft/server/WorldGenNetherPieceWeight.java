package net.minecraft.server;

class WorldGenNetherPieceWeight {

    public Class a;
    public final int b;
    public int c;
    public int d;
    public boolean e;

    public WorldGenNetherPieceWeight(Class oclass, int i, int j, boolean flag) {
        this.a = oclass;
        this.b = i;
        this.d = j;
        this.e = flag;
    }

    public WorldGenNetherPieceWeight(Class oclass, int i, int j) {
        this(oclass, i, j, false);
    }

    public boolean a(int i) {
        return this.d == 0 || this.c < this.d;
    }

    public boolean a() {
        return this.d == 0 || this.c < this.d;
    }
}

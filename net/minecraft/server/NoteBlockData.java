package net.minecraft.server;

public class NoteBlockData {

    private int a;
    private int b;
    private int c;
    private Block d;
    private int e;
    private int f;

    public NoteBlockData(int i, int j, int k, Block block, int l, int i1) {
        this.a = i;
        this.b = j;
        this.c = k;
        this.e = l;
        this.f = i1;
        this.d = block;
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
        return this.e;
    }

    public int e() {
        return this.f;
    }

    public Block f() {
        return this.d;
    }

    public boolean equals(Object object) {
        if (!(object instanceof NoteBlockData)) {
            return false;
        } else {
            NoteBlockData noteblockdata = (NoteBlockData) object;

            return this.a == noteblockdata.a && this.b == noteblockdata.b && this.c == noteblockdata.c && this.e == noteblockdata.e && this.f == noteblockdata.f && this.d == noteblockdata.d;
        }
    }

    public String toString() {
        return "TE(" + this.a + "," + this.b + "," + this.c + ")," + this.e + "," + this.f + "," + this.d;
    }
}

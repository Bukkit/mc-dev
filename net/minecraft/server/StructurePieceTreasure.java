package net.minecraft.server;

public class StructurePieceTreasure extends WeightedRandomChoice {

    public int a;
    public int b;
    public int c;
    public int e;

    public StructurePieceTreasure(int i, int j, int k, int l, int i1) {
        super(i1);
        this.a = i;
        this.b = j;
        this.c = k;
        this.e = l;
    }
}

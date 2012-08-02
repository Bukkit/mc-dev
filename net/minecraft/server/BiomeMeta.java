package net.minecraft.server;

public class BiomeMeta extends WeightedRandomChoice {

    public Class b;
    public int c;
    public int d;

    public BiomeMeta(Class oclass, int i, int j, int k) {
        super(i);
        this.b = oclass;
        this.c = j;
        this.d = k;
    }
}

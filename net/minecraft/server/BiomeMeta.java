package net.minecraft.server;

public class BiomeMeta extends WeightedRandomChoice {

    public Class a;
    public int b;
    public int c;

    public BiomeMeta(Class oclass, int i, int j, int k) {
        super(i);
        this.a = oclass;
        this.b = j;
        this.c = k;
    }
}

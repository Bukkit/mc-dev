package net.minecraft.server;

public class BiomeTheEnd extends BiomeBase {

    public BiomeTheEnd(int i) {
        super(i);
        this.as.clear();
        this.at.clear();
        this.au.clear();
        this.av.clear();
        this.as.add(new BiomeMeta(EntityEnderman.class, 10, 4, 4));
        this.ai = Blocks.DIRT;
        this.ak = Blocks.DIRT;
        this.ar = new BiomeTheEndDecorator();
    }
}

package net.minecraft.server;

public class BiomeTheEnd extends BiomeBase {

    public BiomeTheEnd(int i) {
        super(i);
        this.H.clear();
        this.I.clear();
        this.J.clear();
        this.H.add(new BiomeMeta(EntityEnderman.class, 10, 4, 4));
        this.y = (byte) Block.DIRT.id;
        this.z = (byte) Block.DIRT.id;
        this.G = new BiomeTheEndDecorator(this);
    }
}

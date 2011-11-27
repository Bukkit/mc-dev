package net.minecraft.server;

public class BiomeTheEnd extends BiomeBase {

    public BiomeTheEnd(int i) {
        super(i);
        this.C.clear();
        this.D.clear();
        this.E.clear();
        this.C.add(new BiomeMeta(EntityEnderman.class, 10, 4, 4));
        this.t = (byte) Block.DIRT.id;
        this.u = (byte) Block.DIRT.id;
        this.B = new BiomeTheEndDecorator(this);
    }
}

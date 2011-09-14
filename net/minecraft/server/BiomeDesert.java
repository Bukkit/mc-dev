package net.minecraft.server;

public class BiomeDesert extends BiomeBase {

    public BiomeDesert(int i) {
        super(i);
        this.w.clear();
        this.n = (byte) Block.SAND.id;
        this.o = (byte) Block.SAND.id;
        this.u.r = -999;
        this.u.u = 2;
        this.u.w = 50;
        this.u.x = 10;
    }
}

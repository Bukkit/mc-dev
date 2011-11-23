package net.minecraft.server;

public class BiomeDesert extends BiomeBase {

    public BiomeDesert(int i) {
        super(i);
        this.D.clear();
        this.t = (byte) Block.SAND.id;
        this.u = (byte) Block.SAND.id;
        this.B.z = -999;
        this.B.C = 2;
        this.B.E = 50;
        this.B.F = 10;
    }
}

package net.minecraft.server;

public class BiomeDesert extends BiomeBase {

    public BiomeDesert(int i) {
        super(i);
        this.I.clear();
        this.y = (byte) Block.SAND.id;
        this.z = (byte) Block.SAND.id;
        this.G.z = -999;
        this.G.C = 2;
        this.G.E = 50;
        this.G.F = 10;
    }
}

package net.minecraft.server;

public class BiomeBeach extends BiomeBase {

    public BiomeBeach(int i) {
        super(i);
        this.I.clear();
        this.y = (byte) Block.SAND.id;
        this.z = (byte) Block.SAND.id;
        this.G.z = -999;
        this.G.C = 0;
        this.G.E = 0;
        this.G.F = 0;
    }
}

package net.minecraft.server;

public class BiomeBeach extends BiomeBase {

    public BiomeBeach(int i) {
        super(i);
        this.at.clear();
        this.ai = Blocks.SAND;
        this.ak = Blocks.SAND;
        this.ar.x = -999;
        this.ar.A = 0;
        this.ar.C = 0;
        this.ar.D = 0;
    }
}

package net.minecraft.server;

public class BiomeTemperature {

    public float a;
    public float b;

    public BiomeTemperature(float f, float f1) {
        this.a = f;
        this.b = f1;
    }

    public BiomeTemperature a() {
        return new BiomeTemperature(this.a * 0.8F, this.b * 0.6F);
    }
}

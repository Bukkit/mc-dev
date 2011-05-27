package net.minecraft.server;

public class WorldMapOrienter {

    public byte a;
    public byte b;
    public byte c;
    public byte d;

    final WorldMap e;

    public WorldMapOrienter(WorldMap worldmap, byte b0, byte b1, byte b2, byte b3) {
        this.e = worldmap;
        this.a = b0;
        this.b = b1;
        this.c = b2;
        this.d = b3;
    }
}

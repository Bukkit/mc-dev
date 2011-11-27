package net.minecraft.server;

public class WorldMapDecoration {

    public byte type;
    public byte locX;
    public byte locY;
    public byte rotation;

    final WorldMap e;

    public WorldMapDecoration(WorldMap worldmap, byte b0, byte b1, byte b2, byte b3) {
        this.e = worldmap;
        this.type = b0;
        this.locX = b1;
        this.locY = b2;
        this.rotation = b3;
    }
}

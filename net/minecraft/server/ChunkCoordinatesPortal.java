package net.minecraft.server;

public class ChunkCoordinatesPortal extends ChunkCoordinates {

    public long d;
    final PortalTravelAgent e;

    public ChunkCoordinatesPortal(PortalTravelAgent portaltravelagent, int i, int j, int k, long l) {
        super(i, j, k);
        this.e = portaltravelagent;
        this.d = l;
    }
}

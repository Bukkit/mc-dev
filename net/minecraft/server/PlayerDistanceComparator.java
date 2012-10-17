package net.minecraft.server;

import java.util.Comparator;

public class PlayerDistanceComparator implements Comparator {

    private final ChunkCoordinates a;

    public PlayerDistanceComparator(ChunkCoordinates chunkcoordinates) {
        this.a = chunkcoordinates;
    }

    public int a(EntityPlayer entityplayer, EntityPlayer entityplayer1) {
        double d0 = entityplayer.e((double) this.a.x, (double) this.a.y, (double) this.a.z);
        double d1 = entityplayer1.e((double) this.a.x, (double) this.a.y, (double) this.a.z);

        return d0 < d1 ? -1 : (d0 > d1 ? 1 : 0);
    }

    public int compare(Object object, Object object1) {
        return this.a((EntityPlayer) object, (EntityPlayer) object1);
    }
}

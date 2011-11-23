package net.minecraft.server;

class PendingChunkToSave {

    public final ChunkCoordIntPair a;
    public final NBTTagCompound b;

    public PendingChunkToSave(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) {
        this.a = chunkcoordintpair;
        this.b = nbttagcompound;
    }
}

package net.minecraft.server;

public class WorldProviderTheEnd extends WorldProvider {

    public WorldProviderTheEnd() {}

    public void a() {
        this.b = new WorldChunkManagerHell(BiomeBase.SKY, 0.5F, 0.0F);
        this.dimension = 1;
        this.e = true;
        this.c = true;
    }

    public IChunkProvider getChunkProvider() {
        return new ChunkProviderTheEnd(this.a, this.a.getSeed());
    }

    public float a(long i, float f) {
        return 0.0F;
    }

    public boolean c() {
        return false;
    }

    public boolean canSpawn(int i, int j) {
        int k = this.a.a(i, j);

        return k == 0 ? false : Block.byId[k].material.isSolid();
    }

    public ChunkCoordinates d() {
        return new ChunkCoordinates(100, 50, 0);
    }
}

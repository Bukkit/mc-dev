package net.minecraft.server;

public class WorldProviderSky extends WorldProvider {

    public WorldProviderSky() {}

    public void a() {
        this.b = new WorldChunkManagerHell(BiomeBase.SKY, 0.5F, 0.0F);
        this.dimension = 1;
        this.e = true;
        this.c = true;
    }

    public IChunkProvider getChunkProvider() {
        return new ChunkProviderSky(this.a, this.a.getSeed());
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

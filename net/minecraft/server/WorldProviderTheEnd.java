package net.minecraft.server;

public class WorldProviderTheEnd extends WorldProvider {

    public WorldProviderTheEnd() {}

    public void b() {
        this.d = new WorldChunkManagerHell(BiomeBase.SKY, 0.5F, 0.0F);
        this.dimension = 1;
        this.f = true;
    }

    public IChunkProvider getChunkProvider() {
        return new ChunkProviderTheEnd(this.a, this.a.getSeed());
    }

    public float a(long i, float f) {
        return 0.0F;
    }

    public boolean e() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public boolean canSpawn(int i, int j) {
        int k = this.a.b(i, j);

        return k == 0 ? false : Block.byId[k].material.isSolid();
    }

    public ChunkCoordinates h() {
        return new ChunkCoordinates(100, 50, 0);
    }

    public int getSeaLevel() {
        return 50;
    }

    public String getName() {
        return "The End";
    }
}

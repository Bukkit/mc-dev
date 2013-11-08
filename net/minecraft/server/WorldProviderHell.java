package net.minecraft.server;

public class WorldProviderHell extends WorldProvider {

    public WorldProviderHell() {}

    public void b() {
        this.e = new WorldChunkManagerHell(BiomeBase.HELL, 0.0F);
        this.f = true;
        this.g = true;
        this.dimension = -1;
    }

    protected void a() {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0F - (float) i / 15.0F;

            this.h[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    public IChunkProvider getChunkProvider() {
        return new ChunkProviderHell(this.b, this.b.getSeed());
    }

    public boolean d() {
        return false;
    }

    public boolean canSpawn(int i, int j) {
        return false;
    }

    public float a(long i, float f) {
        return 0.5F;
    }

    public boolean e() {
        return false;
    }

    public String getName() {
        return "Nether";
    }
}

package net.minecraft.server;

public class WorldProviderHell extends WorldProvider {

    public WorldProviderHell() {}

    public void a() {
        this.b = new WorldChunkManagerHell(BiomeBase.HELL, 1.0D, 0.0D);
        this.c = true;
        this.d = true;
        this.e = true;
        this.dimension = -1;
    }

    protected void c() {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0F - (float) i / 15.0F;

            this.f[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    public IChunkProvider getChunkProvider() {
        return new ChunkProviderHell(this.a, this.a.getSeed());
    }

    public boolean canSpawn(int i, int j) {
        int k = this.a.a(i, j);

        return k == Block.BEDROCK.id ? false : (k == 0 ? false : Block.o[k]);
    }

    public float a(long i, float f) {
        return 0.5F;
    }

    public boolean d() {
        return false;
    }
}

package net.minecraft.server;

import java.io.File;

public class WorldProviderHell extends WorldProvider {

    public WorldProviderHell() {}

    public void a() {
        this.b = new WorldChunkManagerHell(BiomeBase.HELL, 1.0D, 0.0D);
        this.c = true;
        this.e = -1;
    }

    protected void b() {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0F - (float) i / 15.0F;

            this.d[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    public IChunkProvider c() {
        return new ChunkProviderHell(this.a, this.a.u);
    }

    public IChunkLoader a(File file1) {
        File file2 = new File(file1, "DIM-1");

        file2.mkdirs();
        return new ChunkLoader(file2, true);
    }

    public boolean a(int i, int j) {
        int k = this.a.a(i, j);

        return k == Block.BEDROCK.bi ? false : (k == 0 ? false : Block.p[k]);
    }

    public float a(long i, float f) {
        return 0.5F;
    }
}

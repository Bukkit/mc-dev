package net.minecraft.server;

import java.io.File;

public class WorldProvider {

    public World a;
    public WorldChunkManager b;
    public boolean c = false;
    public float[] d = new float[16];
    public int e = 0;
    private float[] f = new float[4];

    public WorldProvider() {}

    public final void a(World world) {
        this.a = world;
        this.a();
        this.b();
    }

    protected void b() {
        float f = 0.05F;

        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0F - (float) i / 15.0F;

            this.d[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    protected void a() {
        this.b = new WorldChunkManager(this.a);
    }

    public IChunkProvider c() {
        return new ChunkProviderGenerate(this.a, this.a.u);
    }

    public IChunkLoader a(File file1) {
        return new ChunkLoader(file1, true);
    }

    public boolean a(int i, int j) {
        int k = this.a.a(i, j);

        return k == Block.SAND.bi;
    }

    public float a(long i, float f) {
        int j = (int) (i % 24000L);
        float f1 = ((float) j + f) / 24000.0F - 0.25F;

        if (f1 < 0.0F) {
            ++f1;
        }

        if (f1 > 1.0F) {
            --f1;
        }

        float f2 = f1;

        f1 = 1.0F - (float) ((Math.cos((double) f1 * 3.141592653589793D) + 1.0D) / 2.0D);
        f1 = f2 + (f1 - f2) / 3.0F;
        return f1;
    }

    public static WorldProvider a(int i) {
        return (WorldProvider) (i == 0 ? new WorldProvider() : (i == -1 ? new WorldProviderHell() : null));
    }
}

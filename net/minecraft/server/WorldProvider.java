package net.minecraft.server;

public abstract class WorldProvider {

    public World a;
    public WorldType type;
    public String c;
    public WorldChunkManager d;
    public boolean e = false;
    public boolean f = false;
    public float[] g = new float[16];
    public int dimension = 0;
    private float[] i = new float[4];

    public WorldProvider() {}

    public final void a(World world) {
        this.a = world;
        this.type = world.getWorldData().getType();
        this.c = world.getWorldData().getGeneratorOptions();
        this.b();
        this.a();
    }

    protected void a() {
        float f = 0.0F;

        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0F - (float) i / 15.0F;

            this.g[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    protected void b() {
        if (this.a.getWorldData().getType() == WorldType.FLAT) {
            WorldGenFlatInfo worldgenflatinfo = WorldGenFlatInfo.a(this.a.getWorldData().getGeneratorOptions());

            this.d = new WorldChunkManagerHell(BiomeBase.biomes[worldgenflatinfo.a()], 0.5F, 0.5F);
        } else {
            this.d = new WorldChunkManager(this.a);
        }
    }

    public IChunkProvider getChunkProvider() {
        return (IChunkProvider) (this.type == WorldType.FLAT ? new ChunkProviderFlat(this.a, this.a.getSeed(), this.a.getWorldData().shouldGenerateMapFeatures(), this.c) : new ChunkProviderGenerate(this.a, this.a.getSeed(), this.a.getWorldData().shouldGenerateMapFeatures()));
    }

    public boolean canSpawn(int i, int j) {
        int k = this.a.b(i, j);

        return k == Block.GRASS.id;
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

    public boolean d() {
        return true;
    }

    public boolean e() {
        return true;
    }

    public static WorldProvider byDimension(int i) {
        return (WorldProvider) (i == -1 ? new WorldProviderHell() : (i == 0 ? new WorldProviderNormal() : (i == 1 ? new WorldProviderTheEnd() : null)));
    }

    public ChunkCoordinates h() {
        return null;
    }

    public int getSeaLevel() {
        return this.type == WorldType.FLAT ? 4 : 64;
    }

    public abstract String getName();
}

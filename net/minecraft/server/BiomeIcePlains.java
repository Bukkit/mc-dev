package net.minecraft.server;

import java.util.Random;

public class BiomeIcePlains extends BiomeBase {

    private boolean aC;
    private WorldGenPackedIce2 aD = new WorldGenPackedIce2();
    private WorldGenPackedIce1 aE = new WorldGenPackedIce1(4);

    public BiomeIcePlains(int i, boolean flag) {
        super(i);
        this.aC = flag;
        if (flag) {
            this.ai = Blocks.SNOW_BLOCK;
        }

        this.at.clear();
    }

    public void a(World world, Random random, int i, int j) {
        if (this.aC) {
            int k;
            int l;
            int i1;

            for (k = 0; k < 3; ++k) {
                l = i + random.nextInt(16) + 8;
                i1 = j + random.nextInt(16) + 8;
                this.aD.a(world, random, l, world.getHighestBlockYAt(l, i1), i1);
            }

            for (k = 0; k < 2; ++k) {
                l = i + random.nextInt(16) + 8;
                i1 = j + random.nextInt(16) + 8;
                this.aE.a(world, random, l, world.getHighestBlockYAt(l, i1), i1);
            }
        }

        super.a(world, random, i, j);
    }

    public WorldGenTreeAbstract a(Random random) {
        return new WorldGenTaiga2(false);
    }

    protected BiomeBase k() {
        BiomeBase biomebase = (new BiomeIcePlains(this.id + 128, true)).a(13828095, true).a(this.af + " Spikes").c().a(0.0F, 0.5F).a(new BiomeTemperature(this.am + 0.1F, this.an + 0.1F));

        biomebase.am = this.am + 0.3F;
        biomebase.an = this.an + 0.4F;
        return biomebase;
    }
}

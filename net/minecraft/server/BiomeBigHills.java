package net.minecraft.server;

import java.util.Random;

public class BiomeBigHills extends BiomeBase {

    private WorldGenerator aC;
    private WorldGenTaiga2 aD;
    private int aE;
    private int aF;
    private int aG;
    private int aH;

    protected BiomeBigHills(int i, boolean flag) {
        super(i);
        this.aC = new WorldGenMinable(Blocks.MONSTER_EGGS, 8);
        this.aD = new WorldGenTaiga2(false);
        this.aE = 0;
        this.aF = 1;
        this.aG = 2;
        this.aH = this.aE;
        if (flag) {
            this.ar.x = 3;
            this.aH = this.aF;
        }
    }

    public WorldGenTreeAbstract a(Random random) {
        return (WorldGenTreeAbstract) (random.nextInt(3) > 0 ? this.aD : super.a(random));
    }

    public void a(World world, Random random, int i, int j) {
        super.a(world, random, i, j);
        int k = 3 + random.nextInt(6);

        int l;
        int i1;
        int j1;

        for (l = 0; l < k; ++l) {
            i1 = i + random.nextInt(16);
            j1 = random.nextInt(28) + 4;
            int k1 = j + random.nextInt(16);

            if (world.getType(i1, j1, k1) == Blocks.STONE) {
                world.setTypeAndData(i1, j1, k1, Blocks.EMERALD_ORE, 0, 2);
            }
        }

        for (k = 0; k < 7; ++k) {
            l = i + random.nextInt(16);
            i1 = random.nextInt(64);
            j1 = j + random.nextInt(16);
            this.aC.a(world, random, l, i1, j1);
        }
    }

    public void a(World world, Random random, Block[] ablock, byte[] abyte, int i, int j, double d0) {
        this.ai = Blocks.GRASS;
        this.aj = 0;
        this.ak = Blocks.DIRT;
        if ((d0 < -1.0D || d0 > 2.0D) && this.aH == this.aG) {
            this.ai = Blocks.GRAVEL;
            this.ak = Blocks.GRAVEL;
        } else if (d0 > 1.0D && this.aH != this.aF) {
            this.ai = Blocks.STONE;
            this.ak = Blocks.STONE;
        }

        this.b(world, random, ablock, abyte, i, j, d0);
    }

    private BiomeBigHills b(BiomeBase biomebase) {
        this.aH = this.aG;
        this.a(biomebase.ag, true);
        this.a(biomebase.af + " M");
        this.a(new BiomeTemperature(biomebase.am, biomebase.an));
        this.a(biomebase.temperature, biomebase.humidity);
        return this;
    }

    protected BiomeBase k() {
        return (new BiomeBigHills(this.id + 128, false)).b(this);
    }
}

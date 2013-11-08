package net.minecraft.server;

import java.util.Random;

public class BiomeTaiga extends BiomeBase {

    private static final WorldGenTaiga1 aC = new WorldGenTaiga1();
    private static final WorldGenTaiga2 aD = new WorldGenTaiga2(false);
    private static final WorldGenMegaTree aE = new WorldGenMegaTree(false, false);
    private static final WorldGenMegaTree aF = new WorldGenMegaTree(false, true);
    private static final WorldGenTaigaStructure aG = new WorldGenTaigaStructure(Blocks.MOSSY_COBBLESTONE, 0);
    private int aH;

    public BiomeTaiga(int i, int j) {
        super(i);
        this.aH = j;
        this.at.add(new BiomeMeta(EntityWolf.class, 8, 4, 4));
        this.ar.x = 10;
        if (j != 1 && j != 2) {
            this.ar.z = 1;
            this.ar.B = 1;
        } else {
            this.ar.z = 7;
            this.ar.A = 1;
            this.ar.B = 3;
        }
    }

    public WorldGenTreeAbstract a(Random random) {
        return (WorldGenTreeAbstract) ((this.aH == 1 || this.aH == 2) && random.nextInt(3) == 0 ? (this.aH != 2 && random.nextInt(13) != 0 ? aE : aF) : (random.nextInt(3) == 0 ? aC : aD));
    }

    public WorldGenerator b(Random random) {
        return random.nextInt(5) > 0 ? new WorldGenGrass(Blocks.LONG_GRASS, 2) : new WorldGenGrass(Blocks.LONG_GRASS, 1);
    }

    public void a(World world, Random random, int i, int j) {
        int k;
        int l;
        int i1;
        int j1;

        if (this.aH == 1 || this.aH == 2) {
            k = random.nextInt(3);

            for (l = 0; l < k; ++l) {
                i1 = i + random.nextInt(16) + 8;
                j1 = j + random.nextInt(16) + 8;
                int k1 = world.getHighestBlockYAt(i1, j1);

                aG.a(world, random, i1, k1, j1);
            }
        }

        ae.a(3);

        for (k = 0; k < 7; ++k) {
            l = i + random.nextInt(16) + 8;
            i1 = j + random.nextInt(16) + 8;
            j1 = random.nextInt(world.getHighestBlockYAt(l, i1) + 32);
            ae.a(world, random, l, j1, i1);
        }

        super.a(world, random, i, j);
    }

    public void a(World world, Random random, Block[] ablock, byte[] abyte, int i, int j, double d0) {
        if (this.aH == 1 || this.aH == 2) {
            this.ai = Blocks.GRASS;
            this.aj = 0;
            this.ak = Blocks.DIRT;
            if (d0 > 1.75D) {
                this.ai = Blocks.DIRT;
                this.aj = 1;
            } else if (d0 > -0.95D) {
                this.ai = Blocks.DIRT;
                this.aj = 2;
            }
        }

        this.b(world, random, ablock, abyte, i, j, d0);
    }

    protected BiomeBase k() {
        return this.id == BiomeBase.MEGA_TAIGA.id ? (new BiomeTaiga(this.id + 128, 2)).a(5858897, true).a("Mega Spruce Taiga").a(5159473).a(0.25F, 0.8F).a(new BiomeTemperature(this.am, this.an)) : super.k();
    }
}

package net.minecraft.server;

import java.util.Random;

public class BiomeForest extends BiomeBase {

    private int aF;
    protected static final WorldGenForest aC = new WorldGenForest(false, true);
    protected static final WorldGenForest aD = new WorldGenForest(false, false);
    protected static final WorldGenForestTree aE = new WorldGenForestTree(false);

    public BiomeForest(int i, int j) {
        super(i);
        this.aF = j;
        this.ar.x = 10;
        this.ar.z = 2;
        if (this.aF == 1) {
            this.ar.x = 6;
            this.ar.y = 100;
            this.ar.z = 1;
        }

        this.a(5159473);
        this.a(0.7F, 0.8F);
        if (this.aF == 2) {
            this.ah = 353825;
            this.ag = 3175492;
            this.a(0.6F, 0.6F);
        }

        if (this.aF == 0) {
            this.at.add(new BiomeMeta(EntityWolf.class, 5, 4, 4));
        }

        if (this.aF == 3) {
            this.ar.x = -999;
        }
    }

    protected BiomeBase a(int i, boolean flag) {
        if (this.aF == 2) {
            this.ah = 353825;
            this.ag = i;
            if (flag) {
                this.ah = (this.ah & 16711422) >> 1;
            }

            return this;
        } else {
            return super.a(i, flag);
        }
    }

    public WorldGenTreeAbstract a(Random random) {
        return (WorldGenTreeAbstract) (this.aF == 3 && random.nextInt(3) > 0 ? aE : (this.aF != 2 && random.nextInt(5) != 0 ? this.az : aD));
    }

    public String a(Random random, int i, int j, int k) {
        if (this.aF == 1) {
            double d0 = MathHelper.a((1.0D + ad.a((double) i / 48.0D, (double) k / 48.0D)) / 2.0D, 0.0D, 0.9999D);
            int l = (int) (d0 * (double) BlockFlowers.a.length);

            if (l == 1) {
                l = 0;
            }

            return BlockFlowers.a[l];
        } else {
            return super.a(random, i, j, k);
        }
    }

    public void a(World world, Random random, int i, int j) {
        int k;
        int l;
        int i1;
        int j1;
        int k1;

        if (this.aF == 3) {
            for (k = 0; k < 4; ++k) {
                for (l = 0; l < 4; ++l) {
                    i1 = i + k * 4 + 1 + 8 + random.nextInt(3);
                    j1 = j + l * 4 + 1 + 8 + random.nextInt(3);
                    k1 = world.getHighestBlockYAt(i1, j1);
                    if (random.nextInt(20) == 0) {
                        WorldGenHugeMushroom worldgenhugemushroom = new WorldGenHugeMushroom();

                        worldgenhugemushroom.a(world, random, i1, k1, j1);
                    } else {
                        WorldGenTreeAbstract worldgentreeabstract = this.a(random);

                        worldgentreeabstract.a(1.0D, 1.0D, 1.0D);
                        if (worldgentreeabstract.a(world, random, i1, k1, j1)) {
                            worldgentreeabstract.b(world, random, i1, k1, j1);
                        }
                    }
                }
            }
        }

        k = random.nextInt(5) - 3;
        if (this.aF == 1) {
            k += 2;
        }

        l = 0;

        while (l < k) {
            i1 = random.nextInt(3);
            if (i1 == 0) {
                ae.a(1);
            } else if (i1 == 1) {
                ae.a(4);
            } else if (i1 == 2) {
                ae.a(5);
            }

            j1 = 0;

            while (true) {
                if (j1 < 5) {
                    k1 = i + random.nextInt(16) + 8;
                    int l1 = j + random.nextInt(16) + 8;
                    int i2 = random.nextInt(world.getHighestBlockYAt(k1, l1) + 32);

                    if (!ae.a(world, random, k1, i2, l1)) {
                        ++j1;
                        continue;
                    }
                }

                ++l;
                break;
            }
        }

        super.a(world, random, i, j);
    }

    protected BiomeBase k() {
        if (this.id == BiomeBase.FOREST.id) {
            BiomeForest biomeforest = new BiomeForest(this.id + 128, 1);

            biomeforest.a(new BiomeTemperature(this.am, this.an + 0.2F));
            biomeforest.a("Flower Forest");
            biomeforest.a(6976549, true);
            biomeforest.a(8233509);
            return biomeforest;
        } else {
            return (BiomeBase) (this.id != BiomeBase.BIRCH_FOREST.id && this.id != BiomeBase.BIRCH_FOREST_HILLS.id ? new BiomeBaseSubForest2(this, this.id + 128, this) : new BiomeBaseSubForest(this, this.id + 128, this));
        }
    }
}

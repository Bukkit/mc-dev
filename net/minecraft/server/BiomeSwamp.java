package net.minecraft.server;

import java.util.Random;

public class BiomeSwamp extends BiomeBase {

    protected BiomeSwamp(int i) {
        super(i);
        this.ar.x = 2;
        this.ar.y = 1;
        this.ar.A = 1;
        this.ar.B = 8;
        this.ar.C = 10;
        this.ar.G = 1;
        this.ar.w = 4;
        this.ar.F = 0;
        this.ar.E = 0;
        this.ar.z = 5;
        this.aq = 14745518;
        this.as.add(new BiomeMeta(EntitySlime.class, 1, 1, 1));
    }

    public WorldGenTreeAbstract a(Random random) {
        return this.aB;
    }

    public String a(Random random, int i, int j, int k) {
        return BlockFlowers.a[1];
    }

    public void a(World world, Random random, Block[] ablock, byte[] abyte, int i, int j, double d0) {
        double d1 = ad.a((double) i * 0.25D, (double) j * 0.25D);

        if (d1 > 0.0D) {
            int k = i & 15;
            int l = j & 15;
            int i1 = ablock.length / 256;

            for (int j1 = 255; j1 >= 0; --j1) {
                int k1 = (l * 16 + k) * i1 + j1;

                if (ablock[k1] == null || ablock[k1].getMaterial() != Material.AIR) {
                    if (j1 == 62 && ablock[k1] != Blocks.STATIONARY_WATER) {
                        ablock[k1] = Blocks.STATIONARY_WATER;
                        if (d1 < 0.12D) {
                            ablock[k1 + 1] = Blocks.WATER_LILY;
                        }
                    }
                    break;
                }
            }
        }

        this.b(world, random, ablock, abyte, i, j, d0);
    }
}

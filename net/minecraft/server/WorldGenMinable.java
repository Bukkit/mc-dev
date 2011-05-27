package net.minecraft.server;

import java.util.Random;

public class WorldGenMinable extends WorldGenerator {

    private int a;
    private int b;

    public WorldGenMinable(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        float f = random.nextFloat() * 3.1415927F;
        double d0 = (double) ((float) (i + 8) + MathHelper.a(f) * (float) this.b / 8.0F);
        double d1 = (double) ((float) (i + 8) - MathHelper.a(f) * (float) this.b / 8.0F);
        double d2 = (double) ((float) (k + 8) + MathHelper.b(f) * (float) this.b / 8.0F);
        double d3 = (double) ((float) (k + 8) - MathHelper.b(f) * (float) this.b / 8.0F);
        double d4 = (double) (j + random.nextInt(3) + 2);
        double d5 = (double) (j + random.nextInt(3) + 2);

        for (int l = 0; l <= this.b; ++l) {
            double d6 = d0 + (d1 - d0) * (double) l / (double) this.b;
            double d7 = d4 + (d5 - d4) * (double) l / (double) this.b;
            double d8 = d2 + (d3 - d2) * (double) l / (double) this.b;
            double d9 = random.nextDouble() * (double) this.b / 16.0D;
            double d10 = (double) (MathHelper.a((float) l * 3.1415927F / (float) this.b) + 1.0F) * d9 + 1.0D;
            double d11 = (double) (MathHelper.a((float) l * 3.1415927F / (float) this.b) + 1.0F) * d9 + 1.0D;

            for (int i1 = (int) (d6 - d10 / 2.0D); i1 <= (int) (d6 + d10 / 2.0D); ++i1) {
                for (int j1 = (int) (d7 - d11 / 2.0D); j1 <= (int) (d7 + d11 / 2.0D); ++j1) {
                    for (int k1 = (int) (d8 - d10 / 2.0D); k1 <= (int) (d8 + d10 / 2.0D); ++k1) {
                        double d12 = ((double) i1 + 0.5D - d6) / (d10 / 2.0D);
                        double d13 = ((double) j1 + 0.5D - d7) / (d11 / 2.0D);
                        double d14 = ((double) k1 + 0.5D - d8) / (d10 / 2.0D);

                        if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && world.a(i1, j1, k1) == Block.STONE.bi) {
                            world.a(i1, j1, k1, this.a);
                        }
                    }
                }
            }
        }

        return true;
    }
}

package net.minecraft.server;

import java.util.Random;

public class WorldGenEnder extends WorldGenerator {

    private Block a;

    public WorldGenEnder(Block block) {
        this.a = block;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        if (world.isEmpty(i, j, k) && world.getType(i, j - 1, k) == this.a) {
            int l = random.nextInt(32) + 6;
            int i1 = random.nextInt(4) + 1;

            int j1;
            int k1;
            int l1;
            int i2;

            for (j1 = i - i1; j1 <= i + i1; ++j1) {
                for (k1 = k - i1; k1 <= k + i1; ++k1) {
                    l1 = j1 - i;
                    i2 = k1 - k;
                    if (l1 * l1 + i2 * i2 <= i1 * i1 + 1 && world.getType(j1, j - 1, k1) != this.a) {
                        return false;
                    }
                }
            }

            for (j1 = j; j1 < j + l && j1 < 256; ++j1) {
                for (k1 = i - i1; k1 <= i + i1; ++k1) {
                    for (l1 = k - i1; l1 <= k + i1; ++l1) {
                        i2 = k1 - i;
                        int j2 = l1 - k;

                        if (i2 * i2 + j2 * j2 <= i1 * i1 + 1) {
                            world.setTypeAndData(k1, j1, l1, Blocks.OBSIDIAN, 0, 2);
                        }
                    }
                }
            }

            EntityEnderCrystal entityendercrystal = new EntityEnderCrystal(world);

            entityendercrystal.setPositionRotation((double) ((float) i + 0.5F), (double) (j + l), (double) ((float) k + 0.5F), random.nextFloat() * 360.0F, 0.0F);
            world.addEntity(entityendercrystal);
            world.setTypeAndData(i, j + l, k, Blocks.BEDROCK, 0, 2);
            return true;
        } else {
            return false;
        }
    }
}

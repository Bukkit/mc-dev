package net.minecraft.server;

import java.util.Random;

public class WorldGenPackedIce1 extends WorldGenerator {

    private Block a;
    private int b;

    public WorldGenPackedIce1(int i) {
        this.a = Blocks.PACKED_ICE;
        this.b = i;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        while (world.isEmpty(i, j, k) && j > 2) {
            --j;
        }

        if (world.getType(i, j, k) != Blocks.SNOW_BLOCK) {
            return false;
        } else {
            int l = random.nextInt(this.b - 2) + 2;
            byte b0 = 1;

            for (int i1 = i - l; i1 <= i + l; ++i1) {
                for (int j1 = k - l; j1 <= k + l; ++j1) {
                    int k1 = i1 - i;
                    int l1 = j1 - k;

                    if (k1 * k1 + l1 * l1 <= l * l) {
                        for (int i2 = j - b0; i2 <= j + b0; ++i2) {
                            Block block = world.getType(i1, i2, j1);

                            if (block == Blocks.DIRT || block == Blocks.SNOW_BLOCK || block == Blocks.ICE) {
                                world.setTypeAndData(i1, i2, j1, this.a, 0, 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}

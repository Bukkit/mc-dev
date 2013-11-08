package net.minecraft.server;

import java.util.Random;

public class WorldGenClay extends WorldGenerator {

    private Block a;
    private int b;

    public WorldGenClay(int i) {
        this.a = Blocks.CLAY;
        this.b = i;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        if (world.getType(i, j, k).getMaterial() != Material.WATER) {
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

                            if (block == Blocks.DIRT || block == Blocks.CLAY) {
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

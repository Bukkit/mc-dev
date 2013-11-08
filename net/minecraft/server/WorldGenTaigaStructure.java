package net.minecraft.server;

import java.util.Random;

public class WorldGenTaigaStructure extends WorldGenerator {

    private Block a;
    private int b;

    public WorldGenTaigaStructure(Block block, int i) {
        super(false);
        this.a = block;
        this.b = i;
    }

    public boolean a(World world, Random random, int i, int j, int k) {
        while (true) {
            if (j > 3) {
                label63: {
                    if (!world.isEmpty(i, j - 1, k)) {
                        Block block = world.getType(i, j - 1, k);

                        if (block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.STONE) {
                            break label63;
                        }
                    }

                    --j;
                    continue;
                }
            }

            if (j <= 3) {
                return false;
            }

            int l = this.b;

            for (int i1 = 0; l >= 0 && i1 < 3; ++i1) {
                int j1 = l + random.nextInt(2);
                int k1 = l + random.nextInt(2);
                int l1 = l + random.nextInt(2);
                float f = (float) (j1 + k1 + l1) * 0.333F + 0.5F;

                for (int i2 = i - j1; i2 <= i + j1; ++i2) {
                    for (int j2 = k - l1; j2 <= k + l1; ++j2) {
                        for (int k2 = j - k1; k2 <= j + k1; ++k2) {
                            float f1 = (float) (i2 - i);
                            float f2 = (float) (j2 - k);
                            float f3 = (float) (k2 - j);

                            if (f1 * f1 + f2 * f2 + f3 * f3 <= f * f) {
                                world.setTypeAndData(i2, k2, j2, this.a, 0, 4);
                            }
                        }
                    }
                }

                i += -(l + 1) + random.nextInt(2 + l * 2);
                k += -(l + 1) + random.nextInt(2 + l * 2);
                j += 0 - random.nextInt(2);
            }

            return true;
        }
    }
}

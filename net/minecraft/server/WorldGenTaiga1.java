package net.minecraft.server;

import java.util.Random;

public class WorldGenTaiga1 extends WorldGenerator {

    public WorldGenTaiga1() {}

    public boolean a(World world, Random random, int i, int j, int k) {
        int l = random.nextInt(5) + 7;
        int i1 = l - random.nextInt(2) - 3;
        int j1 = l - i1;
        int k1 = 1 + random.nextInt(j1 + 1);
        boolean flag = true;

        if (j >= 1 && j + l + 1 <= 128) {
            int l1;
            int i2;
            int j2;
            int k2;
            int l2;

            for (l1 = j; l1 <= j + 1 + l && flag; ++l1) {
                boolean flag1 = true;

                if (l1 - j < i1) {
                    l2 = 0;
                } else {
                    l2 = k1;
                }

                for (i2 = i - l2; i2 <= i + l2 && flag; ++i2) {
                    for (j2 = k - l2; j2 <= k + l2 && flag; ++j2) {
                        if (l1 >= 0 && l1 < 128) {
                            k2 = world.a(i2, l1, j2);
                            if (k2 != 0 && k2 != Block.LEAVES.bi) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                l1 = world.a(i, j - 1, k);
                if ((l1 == Block.GRASS.bi || l1 == Block.DIRT.bi) && j < 128 - l - 1) {
                    world.b(i, j - 1, k, Block.DIRT.bi);
                    l2 = 0;

                    for (i2 = j + l; i2 >= j + i1; --i2) {
                        for (j2 = i - l2; j2 <= i + l2; ++j2) {
                            k2 = j2 - i;

                            for (int i3 = k - l2; i3 <= k + l2; ++i3) {
                                int j3 = i3 - k;

                                if ((Math.abs(k2) != l2 || Math.abs(j3) != l2 || l2 <= 0) && !Block.o[world.a(j2, i2, i3)]) {
                                    world.a(j2, i2, i3, Block.LEAVES.bi, 1);
                                }
                            }
                        }

                        if (l2 >= 1 && i2 == j + i1 + 1) {
                            --l2;
                        } else if (l2 < k1) {
                            ++l2;
                        }
                    }

                    for (i2 = 0; i2 < l - 1; ++i2) {
                        j2 = world.a(i, j + i2, k);
                        if (j2 == 0 || j2 == Block.LEAVES.bi) {
                            world.a(i, j + i2, k, Block.LOG.bi, 1);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}

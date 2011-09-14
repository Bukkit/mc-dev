package net.minecraft.server;

import java.util.Random;

public class WorldGenHugeMushroom extends WorldGenerator {

    private int a = -1;

    public WorldGenHugeMushroom(int i) {
        this.a = i;
    }

    public WorldGenHugeMushroom() {}

    public boolean a(World world, Random random, int i, int j, int k) {
        int l = random.nextInt(2);

        if (this.a >= 0) {
            l = this.a;
        }

        int i1 = random.nextInt(3) + 4;
        boolean flag = true;

        if (j >= 1) {
            int b0000 = j + i1 + 1;

            world.getClass();
            if (b0000 <= 128) {
                int k1;
                int l1;
                int i2;
                int j2;

                for (k1 = j; k1 <= j + 1 + i1; ++k1) {
                    byte b0 = 3;

                    if (k1 == j) {
                        b0 = 0;
                    }

                    for (l1 = i - b0; l1 <= i + b0 && flag; ++l1) {
                        for (i2 = k - b0; i2 <= k + b0 && flag; ++i2) {
                            if (k1 >= 0) {
                                world.getClass();
                                if (k1 < 128) {
                                    j2 = world.getTypeId(l1, k1, i2);
                                    if (j2 != 0 && j2 != Block.LEAVES.id) {
                                        flag = false;
                                    }
                                    continue;
                                }
                            }

                            flag = false;
                        }
                    }
                }

                if (!flag) {
                    return false;
                }

                if (!Block.BROWN_MUSHROOM.canPlace(world, i, j, k)) {
                    return false;
                }

                world.setRawTypeId(i, j - 1, k, Block.DIRT.id);
                k1 = j + i1;
                if (l == 1) {
                    k1 = j + i1 - 3;
                }

                int k2;

                for (k2 = k1; k2 <= j + i1; ++k2) {
                    l1 = 1;
                    if (k2 < j + i1) {
                        ++l1;
                    }

                    if (l == 0) {
                        l1 = 3;
                    }

                    for (i2 = i - l1; i2 <= i + l1; ++i2) {
                        for (j2 = k - l1; j2 <= k + l1; ++j2) {
                            int l2 = 5;

                            if (i2 == i - l1) {
                                --l2;
                            }

                            if (i2 == i + l1) {
                                ++l2;
                            }

                            if (j2 == k - l1) {
                                l2 -= 3;
                            }

                            if (j2 == k + l1) {
                                l2 += 3;
                            }

                            if (l == 0 || k2 < j + i1) {
                                if ((i2 == i - l1 || i2 == i + l1) && (j2 == k - l1 || j2 == k + l1)) {
                                    continue;
                                }

                                if (i2 == i - (l1 - 1) && j2 == k - l1) {
                                    l2 = 1;
                                }

                                if (i2 == i - l1 && j2 == k - (l1 - 1)) {
                                    l2 = 1;
                                }

                                if (i2 == i + (l1 - 1) && j2 == k - l1) {
                                    l2 = 3;
                                }

                                if (i2 == i + l1 && j2 == k - (l1 - 1)) {
                                    l2 = 3;
                                }

                                if (i2 == i - (l1 - 1) && j2 == k + l1) {
                                    l2 = 7;
                                }

                                if (i2 == i - l1 && j2 == k + (l1 - 1)) {
                                    l2 = 7;
                                }

                                if (i2 == i + (l1 - 1) && j2 == k + l1) {
                                    l2 = 9;
                                }

                                if (i2 == i + l1 && j2 == k + (l1 - 1)) {
                                    l2 = 9;
                                }
                            }

                            if (l2 == 5 && k2 < j + i1) {
                                l2 = 0;
                            }

                            if ((l2 != 0 || j >= j + i1 - 1) && !Block.o[world.getTypeId(i2, k2, j2)]) {
                                world.setRawTypeIdAndData(i2, k2, j2, Block.BIG_MUSHROOM_1.id + l, l2);
                            }
                        }
                    }
                }

                for (k2 = 0; k2 < i1; ++k2) {
                    l1 = world.getTypeId(i, j + k2, k);
                    if (!Block.o[l1]) {
                        world.setRawTypeIdAndData(i, j + k2, k, Block.BIG_MUSHROOM_1.id + l, 10);
                    }
                }

                return true;
            }
        }

        return false;
    }
}

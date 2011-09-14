package net.minecraft.server;

import java.util.Random;

public class WorldGenSwampTree extends WorldGenerator {

    public WorldGenSwampTree() {}

    public boolean a(World world, Random random, int i, int j, int k) {
        int l;

        for (l = random.nextInt(4) + 5; world.getMaterial(i, j - 1, k) == Material.WATER; --j) {
            ;
        }

        boolean flag = true;

        if (j >= 1) {
            int k1000 = j + l + 1;

            world.getClass();
            if (k1000 <= 128) {
                int j1;
                int k1;
                int l1;
                int i2;

                for (j1 = j; j1 <= j + 1 + l; ++j1) {
                    byte b0 = 1;

                    if (j1 == j) {
                        b0 = 0;
                    }

                    if (j1 >= j + 1 + l - 2) {
                        b0 = 3;
                    }

                    for (k1 = i - b0; k1 <= i + b0 && flag; ++k1) {
                        for (l1 = k - b0; l1 <= k + b0 && flag; ++l1) {
                            if (j1 >= 0) {
                                world.getClass();
                                if (j1 < 128) {
                                    i2 = world.getTypeId(k1, j1, l1);
                                    if (i2 != 0 && i2 != Block.LEAVES.id) {
                                        if (i2 != Block.STATIONARY_WATER.id && i2 != Block.WATER.id) {
                                            flag = false;
                                        } else if (j1 > j) {
                                            flag = false;
                                        }
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

                j1 = world.getTypeId(i, j - 1, k);
                if (j1 == Block.GRASS.id || j1 == Block.DIRT.id) {
                    world.getClass();
                    if (j < 128 - l - 1) {
                        world.setRawTypeId(i, j - 1, k, Block.DIRT.id);

                        int j2;
                        int k2;

                        for (k2 = j - 3 + l; k2 <= j + l; ++k2) {
                            k1 = k2 - (j + l);
                            l1 = 2 - k1 / 2;

                            for (i2 = i - l1; i2 <= i + l1; ++i2) {
                                j2 = i2 - i;

                                for (int l2 = k - l1; l2 <= k + l1; ++l2) {
                                    int i3 = l2 - k;

                                    if ((Math.abs(j2) != l1 || Math.abs(i3) != l1 || random.nextInt(2) != 0 && k1 != 0) && !Block.o[world.getTypeId(i2, k2, l2)]) {
                                        world.setRawTypeId(i2, k2, l2, Block.LEAVES.id);
                                    }
                                }
                            }
                        }

                        for (k2 = 0; k2 < l; ++k2) {
                            k1 = world.getTypeId(i, j + k2, k);
                            if (k1 == 0 || k1 == Block.LEAVES.id || k1 == Block.WATER.id || k1 == Block.STATIONARY_WATER.id) {
                                world.setRawTypeId(i, j + k2, k, Block.LOG.id);
                            }
                        }

                        for (k2 = j - 3 + l; k2 <= j + l; ++k2) {
                            k1 = k2 - (j + l);
                            l1 = 2 - k1 / 2;

                            for (i2 = i - l1; i2 <= i + l1; ++i2) {
                                for (j2 = k - l1; j2 <= k + l1; ++j2) {
                                    if (world.getTypeId(i2, k2, j2) == Block.LEAVES.id) {
                                        if (random.nextInt(4) == 0 && world.getTypeId(i2 - 1, k2, j2) == 0) {
                                            this.a(world, i2 - 1, k2, j2, 8);
                                        }

                                        if (random.nextInt(4) == 0 && world.getTypeId(i2 + 1, k2, j2) == 0) {
                                            this.a(world, i2 + 1, k2, j2, 2);
                                        }

                                        if (random.nextInt(4) == 0 && world.getTypeId(i2, k2, j2 - 1) == 0) {
                                            this.a(world, i2, k2, j2 - 1, 1);
                                        }

                                        if (random.nextInt(4) == 0 && world.getTypeId(i2, k2, j2 + 1) == 0) {
                                            this.a(world, i2, k2, j2 + 1, 4);
                                        }
                                    }
                                }
                            }
                        }

                        return true;
                    }
                }

                return false;
            }
        }

        return false;
    }

    private void a(World world, int i, int j, int k, int l) {
        world.setTypeIdAndData(i, j, k, Block.VINE.id, l);
        int i1 = 4;

        while (true) {
            --j;
            if (world.getTypeId(i, j, k) != 0 || i1 <= 0) {
                return;
            }

            world.setTypeIdAndData(i, j, k, Block.VINE.id, l);
            --i1;
        }
    }
}

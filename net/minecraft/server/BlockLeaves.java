package net.minecraft.server;

import java.util.Random;

public class BlockLeaves extends BlockLeavesBase {

    private int c;
    int[] b;

    protected BlockLeaves(int i, int j) {
        super(i, j, Material.h, false);
        this.c = j;
        this.a(true);
    }

    public void b(World world, int i, int j, int k) {
        byte b0 = 1;
        int l = b0 + 1;

        if (world.a(i - l, j - l, k - l, i + l, j + l, k + l)) {
            for (int i1 = -b0; i1 <= b0; ++i1) {
                for (int j1 = -b0; j1 <= b0; ++j1) {
                    for (int k1 = -b0; k1 <= b0; ++k1) {
                        int l1 = world.a(i + i1, j + j1, k + k1);

                        if (l1 == Block.LEAVES.bh) {
                            world.c(i + i1, j + j1, k + k1, 7);
                        }
                    }
                }
            }
        }
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (!world.z) {
            if (world.b(i, j, k) == 7) {
                byte b0 = 4;
                int l = b0 + 1;
                byte b1 = 32;
                int i1 = b1 * b1;
                int j1 = b1 / 2;

                if (this.b == null) {
                    this.b = new int[b1 * b1 * b1];
                }

                int k1;

                if (world.a(i - l, j - l, k - l, i + l, j + l, k + l)) {
                    int l1;
                    int i2;
                    int j2;

                    for (k1 = -b0; k1 <= b0; ++k1) {
                        for (l1 = -b0; l1 <= b0; ++l1) {
                            for (i2 = -b0; i2 <= b0; ++i2) {
                                j2 = world.a(i + k1, j + l1, k + i2);
                                if (j2 == Block.LOG.bh) {
                                    this.b[(k1 + j1) * i1 + (l1 + j1) * b1 + i2 + j1] = 0;
                                } else if (j2 == Block.LEAVES.bh) {
                                    this.b[(k1 + j1) * i1 + (l1 + j1) * b1 + i2 + j1] = -2;
                                } else {
                                    this.b[(k1 + j1) * i1 + (l1 + j1) * b1 + i2 + j1] = -1;
                                }
                            }
                        }
                    }

                    for (k1 = 1; k1 <= 4; ++k1) {
                        for (l1 = -b0; l1 <= b0; ++l1) {
                            for (i2 = -b0; i2 <= b0; ++i2) {
                                for (j2 = -b0; j2 <= b0; ++j2) {
                                    if (this.b[(l1 + j1) * i1 + (i2 + j1) * b1 + j2 + j1] == k1 - 1) {
                                        if (this.b[(l1 + j1 - 1) * i1 + (i2 + j1) * b1 + j2 + j1] == -2) {
                                            this.b[(l1 + j1 - 1) * i1 + (i2 + j1) * b1 + j2 + j1] = k1;
                                        }

                                        if (this.b[(l1 + j1 + 1) * i1 + (i2 + j1) * b1 + j2 + j1] == -2) {
                                            this.b[(l1 + j1 + 1) * i1 + (i2 + j1) * b1 + j2 + j1] = k1;
                                        }

                                        if (this.b[(l1 + j1) * i1 + (i2 + j1 - 1) * b1 + j2 + j1] == -2) {
                                            this.b[(l1 + j1) * i1 + (i2 + j1 - 1) * b1 + j2 + j1] = k1;
                                        }

                                        if (this.b[(l1 + j1) * i1 + (i2 + j1 + 1) * b1 + j2 + j1] == -2) {
                                            this.b[(l1 + j1) * i1 + (i2 + j1 + 1) * b1 + j2 + j1] = k1;
                                        }

                                        if (this.b[(l1 + j1) * i1 + (i2 + j1) * b1 + (j2 + j1 - 1)] == -2) {
                                            this.b[(l1 + j1) * i1 + (i2 + j1) * b1 + (j2 + j1 - 1)] = k1;
                                        }

                                        if (this.b[(l1 + j1) * i1 + (i2 + j1) * b1 + j2 + j1 + 1] == -2) {
                                            this.b[(l1 + j1) * i1 + (i2 + j1) * b1 + j2 + j1 + 1] = k1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                k1 = this.b[j1 * i1 + j1 * b1 + j1];
                if (k1 >= 0) {
                    world.b(i, j, k, 0);
                } else {
                    this.g(world, i, j, k);
                }
            }
        }
    }

    private void g(World world, int i, int j, int k) {
        this.a_(world, i, j, k, world.b(i, j, k));
        world.d(i, j, k, 0);
    }

    public int a(Random random) {
        return random.nextInt(16) == 0 ? 1 : 0;
    }

    public int a(int i, Random random) {
        return Block.SAPLING.bh;
    }

    public boolean a() {
        return !this.a;
    }

    public void b(World world, int i, int j, int k, Entity entity) {
        super.b(world, i, j, k, entity);
    }
}

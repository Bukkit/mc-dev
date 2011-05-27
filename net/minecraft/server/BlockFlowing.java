package net.minecraft.server;

import java.util.Random;

public class BlockFlowing extends BlockFluids {

    int a = 0;
    boolean[] b = new boolean[4];
    int[] c = new int[4];

    protected BlockFlowing(int i, Material material) {
        super(i, material);
    }

    private void i(World world, int i, int j, int k) {
        int l = world.b(i, j, k);

        world.a(i, j, k, this.bi + 1, l);
        world.b(i, j, k, i, j, k);
        world.f(i, j, k);
    }

    public void a(World world, int i, int j, int k, Random random) {
        int l = this.g(world, i, j, k);
        boolean flag = true;
        int i1;

        if (l > 0) {
            byte b0 = -100;

            this.a = 0;
            int j1 = this.e(world, i - 1, j, k, b0);

            j1 = this.e(world, i + 1, j, k, j1);
            j1 = this.e(world, i, j, k - 1, j1);
            j1 = this.e(world, i, j, k + 1, j1);
            i1 = j1 + this.d;
            if (i1 >= 8 || j1 < 0) {
                i1 = -1;
            }

            if (this.g(world, i, j + 1, k) >= 0) {
                int k1 = this.g(world, i, j + 1, k);

                if (k1 >= 8) {
                    i1 = k1;
                } else {
                    i1 = k1 + 8;
                }
            }

            if (this.a >= 2 && this.bt == Material.f) {
                if (world.d(i, j - 1, k)) {
                    i1 = 0;
                } else if (world.c(i, j - 1, k) == this.bt && world.b(i, j, k) == 0) {
                    i1 = 0;
                }
            }

            if (this.bt == Material.g && l < 8 && i1 < 8 && i1 > l && random.nextInt(4) != 0) {
                i1 = l;
                flag = false;
            }

            if (i1 != l) {
                l = i1;
                if (i1 < 0) {
                    world.d(i, j, k, 0);
                } else {
                    world.b(i, j, k, i1);
                    world.h(i, j, k, this.bi);
                    world.g(i, j, k, this.bi);
                }
            } else if (flag) {
                this.i(world, i, j, k);
            }
        } else {
            this.i(world, i, j, k);
        }

        if (this.l(world, i, j - 1, k)) {
            if (l >= 8) {
                world.b(i, j - 1, k, this.bi, l);
            } else {
                world.b(i, j - 1, k, this.bi, l + 8);
            }
        } else if (l >= 0 && (l == 0 || this.k(world, i, j - 1, k))) {
            boolean[] aboolean = this.j(world, i, j, k);

            i1 = l + this.d;
            if (l >= 8) {
                i1 = 1;
            }

            if (i1 >= 8) {
                return;
            }

            if (aboolean[0]) {
                this.f(world, i - 1, j, k, i1);
            }

            if (aboolean[1]) {
                this.f(world, i + 1, j, k, i1);
            }

            if (aboolean[2]) {
                this.f(world, i, j, k - 1, i1);
            }

            if (aboolean[3]) {
                this.f(world, i, j, k + 1, i1);
            }
        }
    }

    private void f(World world, int i, int j, int k, int l) {
        if (this.l(world, i, j, k)) {
            int i1 = world.a(i, j, k);

            if (i1 > 0) {
                if (this.bt == Material.g) {
                    this.h(world, i, j, k);
                } else {
                    Block.n[i1].a_(world, i, j, k, world.b(i, j, k));
                }
            }

            world.b(i, j, k, this.bi, l);
        }
    }

    private int a(World world, int i, int j, int k, int l, int i1) {
        int j1 = 1000;

        for (int k1 = 0; k1 < 4; ++k1) {
            if ((k1 != 0 || i1 != 1) && (k1 != 1 || i1 != 0) && (k1 != 2 || i1 != 3) && (k1 != 3 || i1 != 2)) {
                int l1 = i;
                int i2 = k;

                if (k1 == 0) {
                    l1 = i - 1;
                }

                if (k1 == 1) {
                    ++l1;
                }

                if (k1 == 2) {
                    i2 = k - 1;
                }

                if (k1 == 3) {
                    ++i2;
                }

                if (!this.k(world, l1, j, i2) && (world.c(l1, j, i2) != this.bt || world.b(l1, j, i2) != 0)) {
                    if (!this.k(world, l1, j - 1, i2)) {
                        return l;
                    }

                    if (l < 4) {
                        int j2 = this.a(world, l1, j, i2, l + 1, k1);

                        if (j2 < j1) {
                            j1 = j2;
                        }
                    }
                }
            }
        }

        return j1;
    }

    private boolean[] j(World world, int i, int j, int k) {
        int l;
        int i1;

        for (l = 0; l < 4; ++l) {
            this.c[l] = 1000;
            i1 = i;
            int j1 = k;

            if (l == 0) {
                i1 = i - 1;
            }

            if (l == 1) {
                ++i1;
            }

            if (l == 2) {
                j1 = k - 1;
            }

            if (l == 3) {
                ++j1;
            }

            if (!this.k(world, i1, j, j1) && (world.c(i1, j, j1) != this.bt || world.b(i1, j, j1) != 0)) {
                if (!this.k(world, i1, j - 1, j1)) {
                    this.c[l] = 0;
                } else {
                    this.c[l] = this.a(world, i1, j, j1, 1, l);
                }
            }
        }

        l = this.c[0];

        for (i1 = 1; i1 < 4; ++i1) {
            if (this.c[i1] < l) {
                l = this.c[i1];
            }
        }

        for (i1 = 0; i1 < 4; ++i1) {
            this.b[i1] = this.c[i1] == l;
        }

        return this.b;
    }

    private boolean k(World world, int i, int j, int k) {
        int l = world.a(i, j, k);

        if (l != Block.WOODEN_DOOR.bi && l != Block.IRON_DOOR_BLOCK.bi && l != Block.SIGN_POST.bi && l != Block.LADDER.bi && l != Block.SUGAR_CANE_BLOCK.bi) {
            if (l == 0) {
                return false;
            } else {
                Material material = Block.n[l].bt;

                return material.a();
            }
        } else {
            return true;
        }
    }

    protected int e(World world, int i, int j, int k, int l) {
        int i1 = this.g(world, i, j, k);

        if (i1 < 0) {
            return l;
        } else {
            if (i1 == 0) {
                ++this.a;
            }

            if (i1 >= 8) {
                i1 = 0;
            }

            return l >= 0 && i1 >= l ? l : i1;
        }
    }

    private boolean l(World world, int i, int j, int k) {
        Material material = world.c(i, j, k);

        return material == this.bt ? false : (material == Material.g ? false : !this.k(world, i, j, k));
    }

    public void e(World world, int i, int j, int k) {
        super.e(world, i, j, k);
        if (world.a(i, j, k) == this.bi) {
            world.h(i, j, k, this.bi);
        }
    }
}

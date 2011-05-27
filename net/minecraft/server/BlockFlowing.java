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
        int i = world.b(i, j, k);

        world.a(i, j, k, this.bc + 1, i);
        world.b(i, j, k, i, j, k);
        world.f(i, j, k);
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (this.bc != 10 && this.bc != 11 || world.a(i, j - 1, k) <= 1) {
            int i = this.g(world, i, j, k);
            boolean j = true;
            int l;

            if (i > 0) {
                byte arrayOfBoolean = -100;

                this.a = 0;
                int arrayOfBoolean1 = this.e(world, i - 1, j, k, arrayOfBoolean);

                arrayOfBoolean1 = this.e(world, i + 1, j, k, arrayOfBoolean1);
                arrayOfBoolean1 = this.e(world, i, j, k - 1, arrayOfBoolean1);
                arrayOfBoolean1 = this.e(world, i, j, k + 1, arrayOfBoolean1);
                l = arrayOfBoolean1 + this.d;
                if (l >= 8 || arrayOfBoolean1 < 0) {
                    l = -1;
                }

                if (this.g(world, i, j + 1, k) >= 0) {
                    int i1 = this.g(world, i, j + 1, k);

                    if (i1 >= 8) {
                        l = i1;
                    } else {
                        l = i1 + 8;
                    }
                }

                if (this.a >= 2 && this.bn == Material.f) {
                    if (world.d(i, j - 1, k)) {
                        l = 0;
                    } else if (world.c(i, j - 1, k) == this.bn && world.b(i, j, k) == 0) {
                        l = 0;
                    }
                }

                if (this.bn == Material.g && i < 8 && l < 8 && l > i && random.nextInt(4) != 0) {
                    l = i;
                    j = false;
                }

                if (l != i) {
                    i = l;
                    if (l < 0) {
                        world.d(i, j, k, 0);
                    } else {
                        world.b(i, j, k, l);
                        world.h(i, j, k, this.bc);
                        world.g(i, j, k, this.bc);
                    }
                } else if (j) {
                    this.i(world, i, j, k);
                }
            } else {
                this.i(world, i, j, k);
            }

            if (this.l(world, i, j - 1, k)) {
                if (i >= 8) {
                    world.b(i, j - 1, k, this.bc, i);
                } else {
                    world.b(i, j - 1, k, this.bc, i + 8);
                }
            } else if (i >= 0 && (i == 0 || this.k(world, i, j - 1, k))) {
                boolean[] arrayOfBoolean2 = this.j(world, i, j, k);

                l = i + this.d;
                if (i >= 8) {
                    l = 1;
                }

                if (l >= 8) {
                    return;
                }

                if (arrayOfBoolean2[0]) {
                    this.f(world, i - 1, j, k, l);
                }

                if (arrayOfBoolean2[1]) {
                    this.f(world, i + 1, j, k, l);
                }

                if (arrayOfBoolean2[2]) {
                    this.f(world, i, j, k - 1, l);
                }

                if (!arrayOfBoolean2[3]) {
                    return;
                }

                this.f(world, i, j, k + 1, l);
            }
        }
    }

    private void f(World world, int i, int j, int k, int l) {
        if (this.l(world, i, j, k)) {
            int i = world.a(i, j, k);

            if (i > 0) {
                if (this.bn == Material.g) {
                    this.h(world, i, j, k);
                } else {
                    Block.n[i].a_(world, i, j, k, world.b(i, j, k));
                }
            }

            world.b(i, j, k, this.bc, l);
        }
    }

    private int a(World world, int i, int j, int k, int l, int i1) {
        int i = 1000;

        for (int j = 0; j < 4; ++j) {
            if ((j != 0 || i1 != 1) && (j != 1 || i1 != 0) && (j != 2 || i1 != 3) && (j != 3 || i1 != 2)) {
                int k = i;
                int i1 = k;

                if (j == 0) {
                    k = i - 1;
                }

                if (j == 1) {
                    ++k;
                }

                if (j == 2) {
                    i1 = k - 1;
                }

                if (j == 3) {
                    ++i1;
                }

                if (!this.k(world, k, j, i1) && (world.c(k, j, i1) != this.bn || world.b(k, j, i1) != 0)) {
                    if (!this.k(world, k, j - 1, i1)) {
                        return l;
                    }

                    if (l < 4) {
                        int i2 = this.a(world, k, j, i1, l + 1, j);

                        if (i2 < i) {
                            i = i2;
                        }
                    }
                }
            }
        }

        return i;
    }

    private boolean[] j(World world, int i, int j, int k) {
        int i;
        int j;

        for (i = 0; i < 4; ++i) {
            this.c[i] = 1000;
            j = i;
            int l = k;

            if (i == 0) {
                j = i - 1;
            }

            if (i == 1) {
                ++j;
            }

            if (i == 2) {
                l = k - 1;
            }

            if (i == 3) {
                ++l;
            }

            if (!this.k(world, j, j, l) && (world.c(j, j, l) != this.bn || world.b(j, j, l) != 0)) {
                if (!this.k(world, j, j - 1, l)) {
                    this.c[i] = 0;
                } else {
                    this.c[i] = this.a(world, j, j, l, 1, i);
                }
            }
        }

        i = this.c[0];

        for (j = 1; j < 4; ++j) {
            if (this.c[j] < i) {
                i = this.c[j];
            }
        }

        for (j = 0; j < 4; ++j) {
            this.b[j] = this.c[j] == i;
        }

        return this.b;
    }

    private boolean k(World world, int i, int j, int k) {
        int i = world.a(i, j, k);

        if (i != Block.WOODEN_DOOR.bc && i != Block.IRON_DOOR_BLOCK.bc && i != Block.SIGN_POST.bc && i != Block.LADDER.bc && i != Block.SUGAR_CANE_BLOCK.bc) {
            if (i == 0) {
                return false;
            } else {
                Material localiq = Block.n[i].bn;

                return localiq.a();
            }
        } else {
            return true;
        }
    }

    protected int e(World world, int i, int j, int k, int l) {
        int i = this.g(world, i, j, k);

        if (i < 0) {
            return l;
        } else {
            if (i == 0) {
                ++this.a;
            }

            if (i >= 8) {
                i = 0;
            }

            return l >= 0 && i >= l ? l : i;
        }
    }

    private boolean l(World world, int i, int j, int k) {
        Material localiq = world.c(i, j, k);

        return localiq == this.bn ? false : (localiq == Material.g ? false : !this.k(world, i, j, k));
    }

    public void e(World world, int i, int j, int k) {
        super.e(world, i, j, k);
        if (world.a(i, j, k) == this.bc) {
            world.h(i, j, k, this.bc);
        }
    }
}

package net.minecraft.server;

import java.util.Random;

public class BlockRedstoneWire extends Block {

    private boolean a = true;

    public BlockRedstoneWire(int i, int j) {
        super(i, j, Material.n);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean a(World world, int i, int j, int k) {
        return world.d(i, j - 1, k);
    }

    private void g(World world, int i, int j, int k) {
        int l = world.b(i, j, k);
        int i1 = 0;

        this.a = false;
        boolean flag = world.n(i, j, k);

        this.a = true;
        int j1;
        int k1;
        int l1;

        if (flag) {
            i1 = 15;
        } else {
            for (j1 = 0; j1 < 4; ++j1) {
                k1 = i;
                l1 = k;
                if (j1 == 0) {
                    k1 = i - 1;
                }

                if (j1 == 1) {
                    ++k1;
                }

                if (j1 == 2) {
                    l1 = k - 1;
                }

                if (j1 == 3) {
                    ++l1;
                }

                i1 = this.f(world, k1, j, l1, i1);
                if (world.d(k1, j, l1) && !world.d(i, j + 1, k)) {
                    i1 = this.f(world, k1, j + 1, l1, i1);
                } else if (!world.d(k1, j, l1)) {
                    i1 = this.f(world, k1, j - 1, l1, i1);
                }
            }

            if (i1 > 0) {
                --i1;
            } else {
                i1 = 0;
            }
        }

        if (l != i1) {
            world.b(i, j, k, i1);
            world.b(i, j, k, i, j, k);
            if (i1 > 0) {
                --i1;
            }

            for (j1 = 0; j1 < 4; ++j1) {
                k1 = i;
                l1 = k;
                int i2 = j - 1;

                if (j1 == 0) {
                    k1 = i - 1;
                }

                if (j1 == 1) {
                    ++k1;
                }

                if (j1 == 2) {
                    l1 = k - 1;
                }

                if (j1 == 3) {
                    ++l1;
                }

                if (world.d(k1, j, l1)) {
                    i2 += 2;
                }

                int j2 = this.f(world, k1, j, l1, -1);

                if (j2 >= 0 && j2 != i1) {
                    this.g(world, k1, j, l1);
                }

                j2 = this.f(world, k1, i2, l1, -1);
                if (j2 >= 0 && j2 != i1) {
                    this.g(world, k1, i2, l1);
                }
            }

            if (l == 0 || i1 == 0) {
                world.g(i, j, k, this.bi);
                world.g(i - 1, j, k, this.bi);
                world.g(i + 1, j, k, this.bi);
                world.g(i, j, k - 1, this.bi);
                world.g(i, j, k + 1, this.bi);
                world.g(i, j - 1, k, this.bi);
                world.g(i, j + 1, k, this.bi);
            }
        }
    }

    private void h(World world, int i, int j, int k) {
        if (world.a(i, j, k) == this.bi) {
            world.g(i, j, k, this.bi);
            world.g(i - 1, j, k, this.bi);
            world.g(i + 1, j, k, this.bi);
            world.g(i, j, k - 1, this.bi);
            world.g(i, j, k + 1, this.bi);
            world.g(i, j - 1, k, this.bi);
            world.g(i, j + 1, k, this.bi);
        }
    }

    public void e(World world, int i, int j, int k) {
        super.e(world, i, j, k);
        if (!world.z) {
            this.g(world, i, j, k);
            world.g(i, j + 1, k, this.bi);
            world.g(i, j - 1, k, this.bi);
            this.h(world, i - 1, j, k);
            this.h(world, i + 1, j, k);
            this.h(world, i, j, k - 1);
            this.h(world, i, j, k + 1);
            if (world.d(i - 1, j, k)) {
                this.h(world, i - 1, j + 1, k);
            } else {
                this.h(world, i - 1, j - 1, k);
            }

            if (world.d(i + 1, j, k)) {
                this.h(world, i + 1, j + 1, k);
            } else {
                this.h(world, i + 1, j - 1, k);
            }

            if (world.d(i, j, k - 1)) {
                this.h(world, i, j + 1, k - 1);
            } else {
                this.h(world, i, j - 1, k - 1);
            }

            if (world.d(i, j, k + 1)) {
                this.h(world, i, j + 1, k + 1);
            } else {
                this.h(world, i, j - 1, k + 1);
            }
        }
    }

    public void b(World world, int i, int j, int k) {
        super.b(world, i, j, k);
        if (!world.z) {
            world.g(i, j + 1, k, this.bi);
            world.g(i, j - 1, k, this.bi);
            this.g(world, i, j, k);
            this.h(world, i - 1, j, k);
            this.h(world, i + 1, j, k);
            this.h(world, i, j, k - 1);
            this.h(world, i, j, k + 1);
            if (world.d(i - 1, j, k)) {
                this.h(world, i - 1, j + 1, k);
            } else {
                this.h(world, i - 1, j - 1, k);
            }

            if (world.d(i + 1, j, k)) {
                this.h(world, i + 1, j + 1, k);
            } else {
                this.h(world, i + 1, j - 1, k);
            }

            if (world.d(i, j, k - 1)) {
                this.h(world, i, j + 1, k - 1);
            } else {
                this.h(world, i, j - 1, k - 1);
            }

            if (world.d(i, j, k + 1)) {
                this.h(world, i, j + 1, k + 1);
            } else {
                this.h(world, i, j - 1, k + 1);
            }
        }
    }

    private int f(World world, int i, int j, int k, int l) {
        if (world.a(i, j, k) != this.bi) {
            return l;
        } else {
            int i1 = world.b(i, j, k);

            return i1 > l ? i1 : l;
        }
    }

    public void b(World world, int i, int j, int k, int l) {
        if (!world.z) {
            int i1 = world.b(i, j, k);
            boolean flag = this.a(world, i, j, k);

            if (!flag) {
                this.a_(world, i, j, k, i1);
                world.d(i, j, k, 0);
            } else {
                this.g(world, i, j, k);
            }

            super.b(world, i, j, k, l);
        }
    }

    public int a(int i, Random random) {
        return Item.REDSTONE.aW;
    }

    public boolean d(World world, int i, int j, int k, int l) {
        return !this.a ? false : this.b((IBlockAccess) world, i, j, k, l);
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        if (!this.a) {
            return false;
        } else if (iblockaccess.b(i, j, k) == 0) {
            return false;
        } else if (l == 1) {
            return true;
        } else {
            boolean flag = b(iblockaccess, i - 1, j, k) || !iblockaccess.d(i - 1, j, k) && b(iblockaccess, i - 1, j - 1, k);
            boolean flag1 = b(iblockaccess, i + 1, j, k) || !iblockaccess.d(i + 1, j, k) && b(iblockaccess, i + 1, j - 1, k);
            boolean flag2 = b(iblockaccess, i, j, k - 1) || !iblockaccess.d(i, j, k - 1) && b(iblockaccess, i, j - 1, k - 1);
            boolean flag3 = b(iblockaccess, i, j, k + 1) || !iblockaccess.d(i, j, k + 1) && b(iblockaccess, i, j - 1, k + 1);

            if (!iblockaccess.d(i, j + 1, k)) {
                if (iblockaccess.d(i - 1, j, k) && b(iblockaccess, i - 1, j + 1, k)) {
                    flag = true;
                }

                if (iblockaccess.d(i + 1, j, k) && b(iblockaccess, i + 1, j + 1, k)) {
                    flag1 = true;
                }

                if (iblockaccess.d(i, j, k - 1) && b(iblockaccess, i, j + 1, k - 1)) {
                    flag2 = true;
                }

                if (iblockaccess.d(i, j, k + 1) && b(iblockaccess, i, j + 1, k + 1)) {
                    flag3 = true;
                }
            }

            return !flag2 && !flag1 && !flag && !flag3 && l >= 2 && l <= 5 ? true : (l == 2 && flag2 && !flag && !flag1 ? true : (l == 3 && flag3 && !flag && !flag1 ? true : (l == 4 && flag && !flag2 && !flag3 ? true : l == 5 && flag1 && !flag2 && !flag3)));
        }
    }

    public boolean c() {
        return this.a;
    }

    public static boolean b(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.a(i, j, k);

        return l == Block.REDSTONE_WIRE.bi ? true : (l == 0 ? false : Block.n[l].c());
    }
}

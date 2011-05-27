package net.minecraft.server;

import java.util.Random;

public class BlockButton extends Block {

    protected BlockButton(int i, int j) {
        super(i, j, Material.n);
        this.a(true);
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return null;
    }

    public int b() {
        return 20;
    }

    public boolean a() {
        return false;
    }

    public boolean a(World world, int i, int j, int k) {
        return world.d(i - 1, j, k) ? true : (world.d(i + 1, j, k) ? true : (world.d(i, j, k - 1) ? true : world.d(i, j, k + 1)));
    }

    public void c(World world, int i, int j, int k, int l) {
        int i1 = world.b(i, j, k);
        int j1 = i1 & 8;

        i1 &= 7;
        if (l == 2 && world.d(i, j, k + 1)) {
            i1 = 4;
        }

        if (l == 3 && world.d(i, j, k - 1)) {
            i1 = 3;
        }

        if (l == 4 && world.d(i + 1, j, k)) {
            i1 = 2;
        }

        if (l == 5 && world.d(i - 1, j, k)) {
            i1 = 1;
        }

        world.b(i, j, k, i1 + j1);
    }

    public void e(World world, int i, int j, int k) {
        if (world.d(i - 1, j, k)) {
            world.b(i, j, k, 1);
        } else if (world.d(i + 1, j, k)) {
            world.b(i, j, k, 2);
        } else if (world.d(i, j, k - 1)) {
            world.b(i, j, k, 3);
        } else if (world.d(i, j, k + 1)) {
            world.b(i, j, k, 4);
        }

        this.g(world, i, j, k);
    }

    public void b(World world, int i, int j, int k, int l) {
        if (this.g(world, i, j, k)) {
            int i1 = world.b(i, j, k) & 7;
            boolean flag = false;

            if (!world.d(i - 1, j, k) && i1 == 1) {
                flag = true;
            }

            if (!world.d(i + 1, j, k) && i1 == 2) {
                flag = true;
            }

            if (!world.d(i, j, k - 1) && i1 == 3) {
                flag = true;
            }

            if (!world.d(i, j, k + 1) && i1 == 4) {
                flag = true;
            }

            if (flag) {
                this.a_(world, i, j, k, world.b(i, j, k));
                world.d(i, j, k, 0);
            }
        }
    }

    private boolean g(World world, int i, int j, int k) {
        if (!this.a(world, i, j, k)) {
            this.a_(world, i, j, k, world.b(i, j, k));
            world.d(i, j, k, 0);
            return false;
        } else {
            return true;
        }
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.b(i, j, k);
        int i1 = l & 7;
        boolean flag = (l & 8) > 0;
        float f = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.1875F;
        float f3 = 0.125F;

        if (flag) {
            f3 = 0.0625F;
        }

        if (i1 == 1) {
            this.a(0.0F, f, 0.5F - f2, f3, f1, 0.5F + f2);
        } else if (i1 == 2) {
            this.a(1.0F - f3, f, 0.5F - f2, 1.0F, f1, 0.5F + f2);
        } else if (i1 == 3) {
            this.a(0.5F - f2, f, 0.0F, 0.5F + f2, f1, f3);
        } else if (i1 == 4) {
            this.a(0.5F - f2, f, 1.0F - f3, 0.5F + f2, f1, 1.0F);
        }
    }

    public void b(World world, int i, int j, int k, EntityHuman entityhuman) {
        this.a(world, i, j, k, entityhuman);
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        int l = world.b(i, j, k);
        int i1 = l & 7;
        int j1 = 8 - (l & 8);

        if (j1 == 0) {
            return true;
        } else {
            world.b(i, j, k, i1 + j1);
            world.b(i, j, k, i, j, k);
            world.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.click", 0.3F, 0.6F);
            world.g(i, j, k, this.bi);
            if (i1 == 1) {
                world.g(i - 1, j, k, this.bi);
            } else if (i1 == 2) {
                world.g(i + 1, j, k, this.bi);
            } else if (i1 == 3) {
                world.g(i, j, k - 1, this.bi);
            } else if (i1 == 4) {
                world.g(i, j, k + 1, this.bi);
            } else {
                world.g(i, j - 1, k, this.bi);
            }

            world.h(i, j, k, this.bi);
            return true;
        }
    }

    public void b(World world, int i, int j, int k) {
        int l = world.b(i, j, k);

        if ((l & 8) > 0) {
            world.g(i, j, k, this.bi);
            int i1 = l & 7;

            if (i1 == 1) {
                world.g(i - 1, j, k, this.bi);
            } else if (i1 == 2) {
                world.g(i + 1, j, k, this.bi);
            } else if (i1 == 3) {
                world.g(i, j, k - 1, this.bi);
            } else if (i1 == 4) {
                world.g(i, j, k + 1, this.bi);
            } else {
                world.g(i, j - 1, k, this.bi);
            }
        }

        super.b(world, i, j, k);
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return (iblockaccess.b(i, j, k) & 8) > 0;
    }

    public boolean d(World world, int i, int j, int k, int l) {
        int i1 = world.b(i, j, k);

        if ((i1 & 8) == 0) {
            return false;
        } else {
            int j1 = i1 & 7;

            return j1 == 5 && l == 1 ? true : (j1 == 4 && l == 2 ? true : (j1 == 3 && l == 3 ? true : (j1 == 2 && l == 4 ? true : j1 == 1 && l == 5)));
        }
    }

    public boolean c() {
        return true;
    }

    public void a(World world, int i, int j, int k, Random random) {
        int l = world.b(i, j, k);

        if ((l & 8) != 0) {
            world.b(i, j, k, l & 7);
            world.g(i, j, k, this.bi);
            int i1 = l & 7;

            if (i1 == 1) {
                world.g(i - 1, j, k, this.bi);
            } else if (i1 == 2) {
                world.g(i + 1, j, k, this.bi);
            } else if (i1 == 3) {
                world.g(i, j, k - 1, this.bi);
            } else if (i1 == 4) {
                world.g(i, j, k + 1, this.bi);
            } else {
                world.g(i, j - 1, k, this.bi);
            }

            world.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.click", 0.3F, 0.5F);
            world.b(i, j, k, i, j, k);
        }
    }
}

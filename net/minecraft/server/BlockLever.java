package net.minecraft.server;

public class BlockLever extends Block {

    protected BlockLever(int i, int j) {
        super(i, j, Material.n);
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean a(World world, int i, int j, int k) {
        return world.d(i - 1, j, k) ? true : (world.d(i + 1, j, k) ? true : (world.d(i, j, k - 1) ? true : (world.d(i, j, k + 1) ? true : world.d(i, j - 1, k))));
    }

    public void c(World world, int i, int j, int k, int l) {
        int i1 = world.b(i, j, k);
        int j1 = i1 & 8;

        i1 &= 7;
        if (l == 1 && world.d(i, j - 1, k)) {
            i1 = 5 + world.l.nextInt(2);
        }

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
        } else if (world.d(i, j - 1, k)) {
            world.b(i, j, k, 5 + world.l.nextInt(2));
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

            if (!world.d(i, j - 1, k) && i1 == 5) {
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
        int l = iblockaccess.b(i, j, k) & 7;
        float f = 0.1875F;

        if (l == 1) {
            this.a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
        } else if (l == 2) {
            this.a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
        } else if (l == 3) {
            this.a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
        } else if (l == 4) {
            this.a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
        } else {
            f = 0.25F;
            this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
        }
    }

    public void b(World world, int i, int j, int k, EntityHuman entityhuman) {
        this.a(world, i, j, k, entityhuman);
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        int l = world.b(i, j, k);
        int i1 = l & 7;
        int j1 = 8 - (l & 8);

        world.b(i, j, k, i1 + j1);
        world.b(i, j, k, i, j, k);
        world.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "random.click", 0.3F, j1 > 0 ? 0.6F : 0.5F);
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

        return true;
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
}

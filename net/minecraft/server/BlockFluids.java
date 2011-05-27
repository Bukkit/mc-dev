package net.minecraft.server;

import java.util.Random;

public abstract class BlockFluids extends Block {

    protected BlockFluids(int i, Material material) {
        super(i, (material == Material.g ? 14 : 12) * 16 + 13, material);
        float f = 0.0F;
        float f1 = 0.0F;

        this.a(0.0F + f1, 0.0F + f, 0.0F + f1, 1.0F + f1, 1.0F + f, 1.0F + f1);
        this.a(true);
    }

    public static float b(int i) {
        if (i >= 8) {
            i = 0;
        }

        float f = (float) (i + 1) / 9.0F;

        return f;
    }

    public int a(int i) {
        return i != 0 && i != 1 ? this.bg + 1 : this.bg;
    }

    protected int g(World world, int i, int j, int k) {
        return world.c(i, j, k) != this.bs ? -1 : world.b(i, j, k);
    }

    protected int b(IBlockAccess iblockaccess, int i, int j, int k) {
        if (iblockaccess.c(i, j, k) != this.bs) {
            return -1;
        } else {
            int l = iblockaccess.b(i, j, k);

            if (l >= 8) {
                l = 0;
            }

            return l;
        }
    }

    public boolean a() {
        return false;
    }

    public boolean a(int i, boolean flag) {
        return flag && i == 0;
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        Material material = iblockaccess.c(i, j, k);

        return material == this.bs ? false : (material == Material.r ? false : (l == 1 ? true : super.a(iblockaccess, i, j, k, l)));
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return null;
    }

    public int a(int i, Random random) {
        return 0;
    }

    public int a(Random random) {
        return 0;
    }

    private Vec3D c(IBlockAccess iblockaccess, int i, int j, int k) {
        Vec3D vec3d = Vec3D.b(0.0D, 0.0D, 0.0D);
        int l = this.b(iblockaccess, i, j, k);

        for (int i1 = 0; i1 < 4; ++i1) {
            int j1 = i;
            int k1 = k;

            if (i1 == 0) {
                j1 = i - 1;
            }

            if (i1 == 1) {
                k1 = k - 1;
            }

            if (i1 == 2) {
                ++j1;
            }

            if (i1 == 3) {
                ++k1;
            }

            int l1 = this.b(iblockaccess, j1, j, k1);
            int i2;

            if (l1 < 0) {
                if (!iblockaccess.c(j1, j, k1).c()) {
                    l1 = this.b(iblockaccess, j1, j - 1, k1);
                    if (l1 >= 0) {
                        i2 = l1 - (l - 8);
                        vec3d = vec3d.c((double) ((j1 - i) * i2), (double) ((j - j) * i2), (double) ((k1 - k) * i2));
                    }
                }
            } else if (l1 >= 0) {
                i2 = l1 - l;
                vec3d = vec3d.c((double) ((j1 - i) * i2), (double) ((j - j) * i2), (double) ((k1 - k) * i2));
            }
        }

        if (iblockaccess.b(i, j, k) >= 8) {
            boolean flag = false;

            if (flag || this.a(iblockaccess, i, j, k - 1, 2)) {
                flag = true;
            }

            if (flag || this.a(iblockaccess, i, j, k + 1, 3)) {
                flag = true;
            }

            if (flag || this.a(iblockaccess, i - 1, j, k, 4)) {
                flag = true;
            }

            if (flag || this.a(iblockaccess, i + 1, j, k, 5)) {
                flag = true;
            }

            if (flag || this.a(iblockaccess, i, j + 1, k - 1, 2)) {
                flag = true;
            }

            if (flag || this.a(iblockaccess, i, j + 1, k + 1, 3)) {
                flag = true;
            }

            if (flag || this.a(iblockaccess, i - 1, j + 1, k, 4)) {
                flag = true;
            }

            if (flag || this.a(iblockaccess, i + 1, j + 1, k, 5)) {
                flag = true;
            }

            if (flag) {
                vec3d = vec3d.b().c(0.0D, -6.0D, 0.0D);
            }
        }

        vec3d = vec3d.b();
        return vec3d;
    }

    public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d) {
        Vec3D vec3d1 = this.c(world, i, j, k);

        vec3d.a += vec3d1.a;
        vec3d.b += vec3d1.b;
        vec3d.c += vec3d1.c;
    }

    public int b() {
        return this.bs == Material.f ? 5 : (this.bs == Material.g ? 30 : 0);
    }

    public void a(World world, int i, int j, int k, Random random) {
        super.a(world, i, j, k, random);
    }

    public void e(World world, int i, int j, int k) {
        this.i(world, i, j, k);
    }

    public void b(World world, int i, int j, int k, int l) {
        this.i(world, i, j, k);
    }

    private void i(World world, int i, int j, int k) {
        if (world.a(i, j, k) == this.bh) {
            if (this.bs == Material.g) {
                boolean flag = false;

                if (flag || world.c(i, j, k - 1) == Material.f) {
                    flag = true;
                }

                if (flag || world.c(i, j, k + 1) == Material.f) {
                    flag = true;
                }

                if (flag || world.c(i - 1, j, k) == Material.f) {
                    flag = true;
                }

                if (flag || world.c(i + 1, j, k) == Material.f) {
                    flag = true;
                }

                if (flag || world.c(i, j + 1, k) == Material.f) {
                    flag = true;
                }

                if (flag) {
                    int l = world.b(i, j, k);

                    if (l == 0) {
                        world.d(i, j, k, Block.OBSIDIAN.bh);
                    } else if (l <= 4) {
                        world.d(i, j, k, Block.COBBLESTONE.bh);
                    }

                    this.h(world, i, j, k);
                }
            }
        }
    }

    protected void h(World world, int i, int j, int k) {
        world.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), "random.fizz", 0.5F, 2.6F + (world.l.nextFloat() - world.l.nextFloat()) * 0.8F);

        for (int l = 0; l < 8; ++l) {
            world.a("largesmoke", (double) i + Math.random(), (double) j + 1.2D, (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }
}

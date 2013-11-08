package net.minecraft.server;

import java.util.Random;

public abstract class BlockFluids extends Block {

    protected BlockFluids(Material material) {
        super(material);
        float f = 0.0F;
        float f1 = 0.0F;

        this.a(0.0F + f1, 0.0F + f, 0.0F + f1, 1.0F + f1, 1.0F + f, 1.0F + f1);
        this.a(true);
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k) {
        return this.material != Material.LAVA;
    }

    public static float b(int i) {
        if (i >= 8) {
            i = 0;
        }

        return (float) (i + 1) / 9.0F;
    }

    protected int e(World world, int i, int j, int k) {
        return world.getType(i, j, k).getMaterial() == this.material ? world.getData(i, j, k) : -1;
    }

    protected int e(IBlockAccess iblockaccess, int i, int j, int k) {
        if (iblockaccess.getType(i, j, k).getMaterial() != this.material) {
            return -1;
        } else {
            int l = iblockaccess.getData(i, j, k);

            if (l >= 8) {
                l = 0;
            }

            return l;
        }
    }

    public boolean d() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public boolean a(int i, boolean flag) {
        return flag && i == 0;
    }

    public boolean d(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        Material material = iblockaccess.getType(i, j, k).getMaterial();

        return material == this.material ? false : (l == 1 ? true : (material == Material.ICE ? false : super.d(iblockaccess, i, j, k, l)));
    }

    public AxisAlignedBB a(World world, int i, int j, int k) {
        return null;
    }

    public int b() {
        return 4;
    }

    public Item getDropType(int i, Random random, int j) {
        return null;
    }

    public int a(Random random) {
        return 0;
    }

    private Vec3D f(IBlockAccess iblockaccess, int i, int j, int k) {
        Vec3D vec3d = iblockaccess.getVec3DPool().create(0.0D, 0.0D, 0.0D);
        int l = this.e(iblockaccess, i, j, k);

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

            int l1 = this.e(iblockaccess, j1, j, k1);
            int i2;

            if (l1 < 0) {
                if (!iblockaccess.getType(j1, j, k1).getMaterial().isSolid()) {
                    l1 = this.e(iblockaccess, j1, j - 1, k1);
                    if (l1 >= 0) {
                        i2 = l1 - (l - 8);
                        vec3d = vec3d.add((double) ((j1 - i) * i2), (double) ((j - j) * i2), (double) ((k1 - k) * i2));
                    }
                }
            } else if (l1 >= 0) {
                i2 = l1 - l;
                vec3d = vec3d.add((double) ((j1 - i) * i2), (double) ((j - j) * i2), (double) ((k1 - k) * i2));
            }
        }

        if (iblockaccess.getData(i, j, k) >= 8) {
            boolean flag = false;

            if (flag || this.d(iblockaccess, i, j, k - 1, 2)) {
                flag = true;
            }

            if (flag || this.d(iblockaccess, i, j, k + 1, 3)) {
                flag = true;
            }

            if (flag || this.d(iblockaccess, i - 1, j, k, 4)) {
                flag = true;
            }

            if (flag || this.d(iblockaccess, i + 1, j, k, 5)) {
                flag = true;
            }

            if (flag || this.d(iblockaccess, i, j + 1, k - 1, 2)) {
                flag = true;
            }

            if (flag || this.d(iblockaccess, i, j + 1, k + 1, 3)) {
                flag = true;
            }

            if (flag || this.d(iblockaccess, i - 1, j + 1, k, 4)) {
                flag = true;
            }

            if (flag || this.d(iblockaccess, i + 1, j + 1, k, 5)) {
                flag = true;
            }

            if (flag) {
                vec3d = vec3d.a().add(0.0D, -6.0D, 0.0D);
            }
        }

        vec3d = vec3d.a();
        return vec3d;
    }

    public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d) {
        Vec3D vec3d1 = this.f(world, i, j, k);

        vec3d.c += vec3d1.c;
        vec3d.d += vec3d1.d;
        vec3d.e += vec3d1.e;
    }

    public int a(World world) {
        return this.material == Material.WATER ? 5 : (this.material == Material.LAVA ? (world.worldProvider.g ? 10 : 30) : 0);
    }

    public void onPlace(World world, int i, int j, int k) {
        this.n(world, i, j, k);
    }

    public void doPhysics(World world, int i, int j, int k, Block block) {
        this.n(world, i, j, k);
    }

    private void n(World world, int i, int j, int k) {
        if (world.getType(i, j, k) == this) {
            if (this.material == Material.LAVA) {
                boolean flag = false;

                if (flag || world.getType(i, j, k - 1).getMaterial() == Material.WATER) {
                    flag = true;
                }

                if (flag || world.getType(i, j, k + 1).getMaterial() == Material.WATER) {
                    flag = true;
                }

                if (flag || world.getType(i - 1, j, k).getMaterial() == Material.WATER) {
                    flag = true;
                }

                if (flag || world.getType(i + 1, j, k).getMaterial() == Material.WATER) {
                    flag = true;
                }

                if (flag || world.getType(i, j + 1, k).getMaterial() == Material.WATER) {
                    flag = true;
                }

                if (flag) {
                    int l = world.getData(i, j, k);

                    if (l == 0) {
                        world.setTypeUpdate(i, j, k, Blocks.OBSIDIAN);
                    } else if (l <= 4) {
                        world.setTypeUpdate(i, j, k, Blocks.COBBLESTONE);
                    }

                    this.fizz(world, i, j, k);
                }
            }
        }
    }

    protected void fizz(World world, int i, int j, int k) {
        world.makeSound((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);

        for (int l = 0; l < 8; ++l) {
            world.addParticle("largesmoke", (double) i + Math.random(), (double) j + 1.2D, (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }
}

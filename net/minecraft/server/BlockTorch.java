package net.minecraft.server;

import java.util.Random;

public class BlockTorch extends Block {

    protected BlockTorch(int i) {
        super(i, Material.ORIENTABLE);
        this.b(true);
        this.a(CreativeModeTab.c);
    }

    public AxisAlignedBB b(World world, int i, int j, int k) {
        return null;
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int d() {
        return 2;
    }

    private boolean m(World world, int i, int j, int k) {
        if (world.w(i, j, k)) {
            return true;
        } else {
            int l = world.getTypeId(i, j, k);

            return l == Block.FENCE.id || l == Block.NETHER_FENCE.id || l == Block.GLASS.id || l == Block.COBBLE_WALL.id;
        }
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return world.c(i - 1, j, k, true) ? true : (world.c(i + 1, j, k, true) ? true : (world.c(i, j, k - 1, true) ? true : (world.c(i, j, k + 1, true) ? true : this.m(world, i, j - 1, k))));
    }

    public int getPlacedData(World world, int i, int j, int k, int l, float f, float f1, float f2, int i1) {
        int j1 = i1;

        if (l == 1 && this.m(world, i, j - 1, k)) {
            j1 = 5;
        }

        if (l == 2 && world.c(i, j, k + 1, true)) {
            j1 = 4;
        }

        if (l == 3 && world.c(i, j, k - 1, true)) {
            j1 = 3;
        }

        if (l == 4 && world.c(i + 1, j, k, true)) {
            j1 = 2;
        }

        if (l == 5 && world.c(i - 1, j, k, true)) {
            j1 = 1;
        }

        return j1;
    }

    public void a(World world, int i, int j, int k, Random random) {
        super.a(world, i, j, k, random);
        if (world.getData(i, j, k) == 0) {
            this.onPlace(world, i, j, k);
        }
    }

    public void onPlace(World world, int i, int j, int k) {
        if (world.getData(i, j, k) == 0) {
            if (world.c(i - 1, j, k, true)) {
                world.setData(i, j, k, 1, 2);
            } else if (world.c(i + 1, j, k, true)) {
                world.setData(i, j, k, 2, 2);
            } else if (world.c(i, j, k - 1, true)) {
                world.setData(i, j, k, 3, 2);
            } else if (world.c(i, j, k + 1, true)) {
                world.setData(i, j, k, 4, 2);
            } else if (this.m(world, i, j - 1, k)) {
                world.setData(i, j, k, 5, 2);
            }
        }

        this.k(world, i, j, k);
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        this.d(world, i, j, k, l);
    }

    protected boolean d(World world, int i, int j, int k, int l) {
        if (this.k(world, i, j, k)) {
            int i1 = world.getData(i, j, k);
            boolean flag = false;

            if (!world.c(i - 1, j, k, true) && i1 == 1) {
                flag = true;
            }

            if (!world.c(i + 1, j, k, true) && i1 == 2) {
                flag = true;
            }

            if (!world.c(i, j, k - 1, true) && i1 == 3) {
                flag = true;
            }

            if (!world.c(i, j, k + 1, true) && i1 == 4) {
                flag = true;
            }

            if (!this.m(world, i, j - 1, k) && i1 == 5) {
                flag = true;
            }

            if (flag) {
                this.c(world, i, j, k, world.getData(i, j, k), 0);
                world.setAir(i, j, k);
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    protected boolean k(World world, int i, int j, int k) {
        if (!this.canPlace(world, i, j, k)) {
            if (world.getTypeId(i, j, k) == this.id) {
                this.c(world, i, j, k, world.getData(i, j, k), 0);
                world.setAir(i, j, k);
            }

            return false;
        } else {
            return true;
        }
    }

    public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
        int l = world.getData(i, j, k) & 7;
        float f = 0.15F;

        if (l == 1) {
            this.a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
        } else if (l == 2) {
            this.a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
        } else if (l == 3) {
            this.a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
        } else if (l == 4) {
            this.a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
        } else {
            f = 0.1F;
            this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
        }

        return super.a(world, i, j, k, vec3d, vec3d1);
    }
}

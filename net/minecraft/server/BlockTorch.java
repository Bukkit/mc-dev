package net.minecraft.server;

import java.util.Random;

public class BlockTorch extends Block {

    protected BlockTorch(int i, int j) {
        super(i, j, Material.ORIENTABLE);
        this.a(true);
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    private boolean g(World world, int i, int j, int k) {
        return world.e(i, j, k) || world.getTypeId(i, j, k) == Block.FENCE.id;
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return world.e(i - 1, j, k) ? true : (world.e(i + 1, j, k) ? true : (world.e(i, j, k - 1) ? true : (world.e(i, j, k + 1) ? true : this.g(world, i, j - 1, k))));
    }

    public void postPlace(World world, int i, int j, int k, int l) {
        int i1 = world.getData(i, j, k);

        if (l == 1 && this.g(world, i, j - 1, k)) {
            i1 = 5;
        }

        if (l == 2 && world.e(i, j, k + 1)) {
            i1 = 4;
        }

        if (l == 3 && world.e(i, j, k - 1)) {
            i1 = 3;
        }

        if (l == 4 && world.e(i + 1, j, k)) {
            i1 = 2;
        }

        if (l == 5 && world.e(i - 1, j, k)) {
            i1 = 1;
        }

        world.setData(i, j, k, i1);
    }

    public void a(World world, int i, int j, int k, Random random) {
        super.a(world, i, j, k, random);
        if (world.getData(i, j, k) == 0) {
            this.c(world, i, j, k);
        }
    }

    public void c(World world, int i, int j, int k) {
        if (world.e(i - 1, j, k)) {
            world.setData(i, j, k, 1);
        } else if (world.e(i + 1, j, k)) {
            world.setData(i, j, k, 2);
        } else if (world.e(i, j, k - 1)) {
            world.setData(i, j, k, 3);
        } else if (world.e(i, j, k + 1)) {
            world.setData(i, j, k, 4);
        } else if (this.g(world, i, j - 1, k)) {
            world.setData(i, j, k, 5);
        }

        this.h(world, i, j, k);
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (this.h(world, i, j, k)) {
            int i1 = world.getData(i, j, k);
            boolean flag = false;

            if (!world.e(i - 1, j, k) && i1 == 1) {
                flag = true;
            }

            if (!world.e(i + 1, j, k) && i1 == 2) {
                flag = true;
            }

            if (!world.e(i, j, k - 1) && i1 == 3) {
                flag = true;
            }

            if (!world.e(i, j, k + 1) && i1 == 4) {
                flag = true;
            }

            if (!this.g(world, i, j - 1, k) && i1 == 5) {
                flag = true;
            }

            if (flag) {
                this.g(world, i, j, k, world.getData(i, j, k));
                world.setTypeId(i, j, k, 0);
            }
        }
    }

    private boolean h(World world, int i, int j, int k) {
        if (!this.canPlace(world, i, j, k)) {
            this.g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
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

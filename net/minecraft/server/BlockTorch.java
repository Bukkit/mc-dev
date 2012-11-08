package net.minecraft.server;

import java.util.Random;

public class BlockTorch extends Block {

    protected BlockTorch(int i, int j) {
        super(i, j, Material.ORIENTABLE);
        this.b(true);
        this.a(CreativeModeTab.c);
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
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

    private boolean l(World world, int i, int j, int k) {
        if (world.v(i, j, k)) {
            return true;
        } else {
            int l = world.getTypeId(i, j, k);

            return l == Block.FENCE.id || l == Block.NETHER_FENCE.id || l == Block.GLASS.id || l == Block.COBBLE_WALL.id;
        }
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return world.b(i - 1, j, k, true) ? true : (world.b(i + 1, j, k, true) ? true : (world.b(i, j, k - 1, true) ? true : (world.b(i, j, k + 1, true) ? true : this.l(world, i, j - 1, k))));
    }

    public int getPlacedData(World world, int i, int j, int k, int l, float f, float f1, float f2, int i1) {
        int j1 = i1;

        if (l == 1 && this.l(world, i, j - 1, k)) {
            j1 = 5;
        }

        if (l == 2 && world.b(i, j, k + 1, true)) {
            j1 = 4;
        }

        if (l == 3 && world.b(i, j, k - 1, true)) {
            j1 = 3;
        }

        if (l == 4 && world.b(i + 1, j, k, true)) {
            j1 = 2;
        }

        if (l == 5 && world.b(i - 1, j, k, true)) {
            j1 = 1;
        }

        return j1;
    }

    public void b(World world, int i, int j, int k, Random random) {
        super.b(world, i, j, k, random);
        if (world.getData(i, j, k) == 0) {
            this.onPlace(world, i, j, k);
        }
    }

    public void onPlace(World world, int i, int j, int k) {
        if (world.getData(i, j, k) == 0) {
            if (world.b(i - 1, j, k, true)) {
                world.setData(i, j, k, 1);
            } else if (world.b(i + 1, j, k, true)) {
                world.setData(i, j, k, 2);
            } else if (world.b(i, j, k - 1, true)) {
                world.setData(i, j, k, 3);
            } else if (world.b(i, j, k + 1, true)) {
                world.setData(i, j, k, 4);
            } else if (this.l(world, i, j - 1, k)) {
                world.setData(i, j, k, 5);
            }
        }

        this.n(world, i, j, k);
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (this.n(world, i, j, k)) {
            int i1 = world.getData(i, j, k);
            boolean flag = false;

            if (!world.b(i - 1, j, k, true) && i1 == 1) {
                flag = true;
            }

            if (!world.b(i + 1, j, k, true) && i1 == 2) {
                flag = true;
            }

            if (!world.b(i, j, k - 1, true) && i1 == 3) {
                flag = true;
            }

            if (!world.b(i, j, k + 1, true) && i1 == 4) {
                flag = true;
            }

            if (!this.l(world, i, j - 1, k) && i1 == 5) {
                flag = true;
            }

            if (flag) {
                this.c(world, i, j, k, world.getData(i, j, k), 0);
                world.setTypeId(i, j, k, 0);
            }
        }
    }

    private boolean n(World world, int i, int j, int k) {
        if (!this.canPlace(world, i, j, k)) {
            if (world.getTypeId(i, j, k) == this.id) {
                this.c(world, i, j, k, world.getData(i, j, k), 0);
                world.setTypeId(i, j, k, 0);
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

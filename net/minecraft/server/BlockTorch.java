package net.minecraft.server;

import java.util.Random;

public class BlockTorch extends Block {

    protected BlockTorch() {
        super(Material.ORIENTABLE);
        this.a(true);
        this.a(CreativeModeTab.c);
    }

    public AxisAlignedBB a(World world, int i, int j, int k) {
        return null;
    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public int b() {
        return 2;
    }

    private boolean m(World world, int i, int j, int k) {
        if (World.a((IBlockAccess) world, i, j, k)) {
            return true;
        } else {
            Block block = world.getType(i, j, k);

            return block == Blocks.FENCE || block == Blocks.NETHER_FENCE || block == Blocks.GLASS || block == Blocks.COBBLE_WALL;
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

        this.e(world, i, j, k);
    }

    public void doPhysics(World world, int i, int j, int k, Block block) {
        this.b(world, i, j, k, block);
    }

    protected boolean b(World world, int i, int j, int k, Block block) {
        if (this.e(world, i, j, k)) {
            int l = world.getData(i, j, k);
            boolean flag = false;

            if (!world.c(i - 1, j, k, true) && l == 1) {
                flag = true;
            }

            if (!world.c(i + 1, j, k, true) && l == 2) {
                flag = true;
            }

            if (!world.c(i, j, k - 1, true) && l == 3) {
                flag = true;
            }

            if (!world.c(i, j, k + 1, true) && l == 4) {
                flag = true;
            }

            if (!this.m(world, i, j - 1, k) && l == 5) {
                flag = true;
            }

            if (flag) {
                this.b(world, i, j, k, world.getData(i, j, k), 0);
                world.setAir(i, j, k);
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    protected boolean e(World world, int i, int j, int k) {
        if (!this.canPlace(world, i, j, k)) {
            if (world.getType(i, j, k) == this) {
                this.b(world, i, j, k, world.getData(i, j, k), 0);
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

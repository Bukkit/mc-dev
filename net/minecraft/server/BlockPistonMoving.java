package net.minecraft.server;

import java.util.Random;

public class BlockPistonMoving extends BlockContainer {

    public BlockPistonMoving(int i) {
        super(i, Material.PISTON);
        this.c(-1.0F);
    }

    public TileEntity b(World world) {
        return null;
    }

    public void onPlace(World world, int i, int j, int k) {}

    public void remove(World world, int i, int j, int k, int l, int i1) {
        TileEntity tileentity = world.getTileEntity(i, j, k);

        if (tileentity instanceof TileEntityPiston) {
            ((TileEntityPiston) tileentity).f();
        } else {
            super.remove(world, i, j, k, l, i1);
        }
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return false;
    }

    public boolean canPlace(World world, int i, int j, int k, int l) {
        return false;
    }

    public int d() {
        return -1;
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (!world.isStatic && world.getTileEntity(i, j, k) == null) {
            world.setAir(i, j, k);
            return true;
        } else {
            return false;
        }
    }

    public int getDropType(int i, Random random, int j) {
        return 0;
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
        if (!world.isStatic) {
            TileEntityPiston tileentitypiston = this.d(world, i, j, k);

            if (tileentitypiston != null) {
                Block.byId[tileentitypiston.a()].c(world, i, j, k, tileentitypiston.p(), 0);
            }
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (!world.isStatic && world.getTileEntity(i, j, k) == null) {
            ;
        }
    }

    public static TileEntity a(int i, int j, int k, boolean flag, boolean flag1) {
        return new TileEntityPiston(i, j, k, flag, flag1);
    }

    public AxisAlignedBB b(World world, int i, int j, int k) {
        TileEntityPiston tileentitypiston = this.d(world, i, j, k);

        if (tileentitypiston == null) {
            return null;
        } else {
            float f = tileentitypiston.a(0.0F);

            if (tileentitypiston.b()) {
                f = 1.0F - f;
            }

            return this.b(world, i, j, k, tileentitypiston.a(), f, tileentitypiston.c());
        }
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        TileEntityPiston tileentitypiston = this.d(iblockaccess, i, j, k);

        if (tileentitypiston != null) {
            Block block = Block.byId[tileentitypiston.a()];

            if (block == null || block == this) {
                return;
            }

            block.updateShape(iblockaccess, i, j, k);
            float f = tileentitypiston.a(0.0F);

            if (tileentitypiston.b()) {
                f = 1.0F - f;
            }

            int l = tileentitypiston.c();

            this.minX = block.u() - (double) ((float) Facing.b[l] * f);
            this.minY = block.w() - (double) ((float) Facing.c[l] * f);
            this.minZ = block.y() - (double) ((float) Facing.d[l] * f);
            this.maxX = block.v() - (double) ((float) Facing.b[l] * f);
            this.maxY = block.x() - (double) ((float) Facing.c[l] * f);
            this.maxZ = block.z() - (double) ((float) Facing.d[l] * f);
        }
    }

    public AxisAlignedBB b(World world, int i, int j, int k, int l, float f, int i1) {
        if (l != 0 && l != this.id) {
            AxisAlignedBB axisalignedbb = Block.byId[l].b(world, i, j, k);

            if (axisalignedbb == null) {
                return null;
            } else {
                if (Facing.b[i1] < 0) {
                    axisalignedbb.a -= (double) ((float) Facing.b[i1] * f);
                } else {
                    axisalignedbb.d -= (double) ((float) Facing.b[i1] * f);
                }

                if (Facing.c[i1] < 0) {
                    axisalignedbb.b -= (double) ((float) Facing.c[i1] * f);
                } else {
                    axisalignedbb.e -= (double) ((float) Facing.c[i1] * f);
                }

                if (Facing.d[i1] < 0) {
                    axisalignedbb.c -= (double) ((float) Facing.d[i1] * f);
                } else {
                    axisalignedbb.f -= (double) ((float) Facing.d[i1] * f);
                }

                return axisalignedbb;
            }
        } else {
            return null;
        }
    }

    private TileEntityPiston d(IBlockAccess iblockaccess, int i, int j, int k) {
        TileEntity tileentity = iblockaccess.getTileEntity(i, j, k);

        return tileentity instanceof TileEntityPiston ? (TileEntityPiston) tileentity : null;
    }
}

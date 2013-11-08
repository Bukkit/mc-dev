package net.minecraft.server;

import java.util.Random;

public class BlockPistonMoving extends BlockContainer {

    public BlockPistonMoving() {
        super(Material.PISTON);
        this.c(-1.0F);
    }

    public TileEntity a(World world, int i) {
        return null;
    }

    public void onPlace(World world, int i, int j, int k) {}

    public void remove(World world, int i, int j, int k, Block block, int l) {
        TileEntity tileentity = world.getTileEntity(i, j, k);

        if (tileentity instanceof TileEntityPiston) {
            ((TileEntityPiston) tileentity).f();
        } else {
            super.remove(world, i, j, k, block, l);
        }
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return false;
    }

    public boolean canPlace(World world, int i, int j, int k, int l) {
        return false;
    }

    public int b() {
        return -1;
    }

    public boolean c() {
        return false;
    }

    public boolean d() {
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

    public Item getDropType(int i, Random random, int j) {
        return null;
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
        if (!world.isStatic) {
            TileEntityPiston tileentitypiston = this.e(world, i, j, k);

            if (tileentitypiston != null) {
                tileentitypiston.a().b(world, i, j, k, tileentitypiston.p(), 0);
            }
        }
    }

    public void doPhysics(World world, int i, int j, int k, Block block) {
        if (!world.isStatic) {
            world.getTileEntity(i, j, k);
        }
    }

    public static TileEntity a(Block block, int i, int j, boolean flag, boolean flag1) {
        return new TileEntityPiston(block, i, j, flag, flag1);
    }

    public AxisAlignedBB a(World world, int i, int j, int k) {
        TileEntityPiston tileentitypiston = this.e(world, i, j, k);

        if (tileentitypiston == null) {
            return null;
        } else {
            float f = tileentitypiston.a(0.0F);

            if (tileentitypiston.b()) {
                f = 1.0F - f;
            }

            return this.a(world, i, j, k, tileentitypiston.a(), f, tileentitypiston.c());
        }
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        TileEntityPiston tileentitypiston = this.e(iblockaccess, i, j, k);

        if (tileentitypiston != null) {
            Block block = tileentitypiston.a();

            if (block == this || block.getMaterial() == Material.AIR) {
                return;
            }

            block.updateShape(iblockaccess, i, j, k);
            float f = tileentitypiston.a(0.0F);

            if (tileentitypiston.b()) {
                f = 1.0F - f;
            }

            int l = tileentitypiston.c();

            this.minX = block.x() - (double) ((float) Facing.b[l] * f);
            this.minY = block.z() - (double) ((float) Facing.c[l] * f);
            this.minZ = block.B() - (double) ((float) Facing.d[l] * f);
            this.maxX = block.y() - (double) ((float) Facing.b[l] * f);
            this.maxY = block.A() - (double) ((float) Facing.c[l] * f);
            this.maxZ = block.C() - (double) ((float) Facing.d[l] * f);
        }
    }

    public AxisAlignedBB a(World world, int i, int j, int k, Block block, float f, int l) {
        if (block != this && block.getMaterial() != Material.AIR) {
            AxisAlignedBB axisalignedbb = block.a(world, i, j, k);

            if (axisalignedbb == null) {
                return null;
            } else {
                if (Facing.b[l] < 0) {
                    axisalignedbb.a -= (double) ((float) Facing.b[l] * f);
                } else {
                    axisalignedbb.d -= (double) ((float) Facing.b[l] * f);
                }

                if (Facing.c[l] < 0) {
                    axisalignedbb.b -= (double) ((float) Facing.c[l] * f);
                } else {
                    axisalignedbb.e -= (double) ((float) Facing.c[l] * f);
                }

                if (Facing.d[l] < 0) {
                    axisalignedbb.c -= (double) ((float) Facing.d[l] * f);
                } else {
                    axisalignedbb.f -= (double) ((float) Facing.d[l] * f);
                }

                return axisalignedbb;
            }
        } else {
            return null;
        }
    }

    private TileEntityPiston e(IBlockAccess iblockaccess, int i, int j, int k) {
        TileEntity tileentity = iblockaccess.getTileEntity(i, j, k);

        return tileentity instanceof TileEntityPiston ? (TileEntityPiston) tileentity : null;
    }
}

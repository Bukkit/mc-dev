package net.minecraft.server;

import java.util.Random;

public class BlockPistonMoving extends BlockContainer {

    public BlockPistonMoving(int i) {
        super(i, Material.PISTON);
        this.c(-1.0F);
    }

    public TileEntity a_() {
        return null;
    }

    public void onPlace(World world, int i, int j, int k) {}

    public void remove(World world, int i, int j, int k) {
        TileEntity tileentity = world.getTileEntity(i, j, k);

        if (tileentity != null && tileentity instanceof TileEntityPiston) {
            ((TileEntityPiston) tileentity).g();
        } else {
            super.remove(world, i, j, k);
        }
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return false;
    }

    public boolean canPlace(World world, int i, int j, int k, int l) {
        return false;
    }

    public int c() {
        return -1;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
        if (!world.isStatic && world.getTileEntity(i, j, k) == null) {
            world.setTypeId(i, j, k, 0);
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
            TileEntityPiston tileentitypiston = this.b(world, i, j, k);

            if (tileentitypiston != null) {
                Block.byId[tileentitypiston.c()].b(world, i, j, k, tileentitypiston.j(), 0);
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

    public AxisAlignedBB e(World world, int i, int j, int k) {
        TileEntityPiston tileentitypiston = this.b(world, i, j, k);

        if (tileentitypiston == null) {
            return null;
        } else {
            float f = tileentitypiston.a(0.0F);

            if (tileentitypiston.e()) {
                f = 1.0F - f;
            }

            return this.b(world, i, j, k, tileentitypiston.c(), f, tileentitypiston.f());
        }
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        TileEntityPiston tileentitypiston = this.b(iblockaccess, i, j, k);

        if (tileentitypiston != null) {
            Block block = Block.byId[tileentitypiston.c()];

            if (block == null || block == this) {
                return;
            }

            block.updateShape(iblockaccess, i, j, k);
            float f = tileentitypiston.a(0.0F);

            if (tileentitypiston.e()) {
                f = 1.0F - f;
            }

            int l = tileentitypiston.f();

            this.minX = block.minX - (double) ((float) Facing.b[l] * f);
            this.minY = block.minY - (double) ((float) Facing.c[l] * f);
            this.minZ = block.minZ - (double) ((float) Facing.d[l] * f);
            this.maxX = block.maxX - (double) ((float) Facing.b[l] * f);
            this.maxY = block.maxY - (double) ((float) Facing.c[l] * f);
            this.maxZ = block.maxZ - (double) ((float) Facing.d[l] * f);
        }
    }

    public AxisAlignedBB b(World world, int i, int j, int k, int l, float f, int i1) {
        if (l != 0 && l != this.id) {
            AxisAlignedBB axisalignedbb = Block.byId[l].e(world, i, j, k);

            if (axisalignedbb == null) {
                return null;
            } else {
                axisalignedbb.a -= (double) ((float) Facing.b[i1] * f);
                axisalignedbb.d -= (double) ((float) Facing.b[i1] * f);
                axisalignedbb.b -= (double) ((float) Facing.c[i1] * f);
                axisalignedbb.e -= (double) ((float) Facing.c[i1] * f);
                axisalignedbb.c -= (double) ((float) Facing.d[i1] * f);
                axisalignedbb.f -= (double) ((float) Facing.d[i1] * f);
                return axisalignedbb;
            }
        } else {
            return null;
        }
    }

    private TileEntityPiston b(IBlockAccess iblockaccess, int i, int j, int k) {
        TileEntity tileentity = iblockaccess.getTileEntity(i, j, k);

        return tileentity != null && tileentity instanceof TileEntityPiston ? (TileEntityPiston) tileentity : null;
    }
}

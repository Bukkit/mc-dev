package net.minecraft.server;

import java.util.Random;

public class BlockPistonMoving extends BlockContainer {

    public BlockPistonMoving(int i) {
        super(i, Material.PISTON);
        this.c(-1.0F);
    }

    protected TileEntity a_() {
        return null;
    }

    public void e(World world, int i, int j, int k) {}

    public void remove(World world, int i, int j, int k) {
        TileEntity tileentity = world.getTileEntity(i, j, k);

        if (tileentity != null && tileentity instanceof TileEntityPiston) {
            ((TileEntityPiston) tileentity).k();
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

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int a(int i, Random random) {
        return 0;
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f) {
        if (!world.isStatic) {
            TileEntityPiston tileentitypiston = this.b(world, i, j, k);

            if (tileentitypiston != null) {
                Block.byId[tileentitypiston.a()].g(world, i, j, k, tileentitypiston.e());
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

    public AxisAlignedBB d(World world, int i, int j, int k) {
        TileEntityPiston tileentitypiston = this.b(world, i, j, k);

        if (tileentitypiston == null) {
            return null;
        } else {
            float f = tileentitypiston.a(0.0F);

            if (tileentitypiston.c()) {
                f = 1.0F - f;
            }

            return this.a(world, i, j, k, tileentitypiston.a(), f, tileentitypiston.d());
        }
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k) {
        TileEntityPiston tileentitypiston = this.b(iblockaccess, i, j, k);

        if (tileentitypiston != null) {
            Block block = Block.byId[tileentitypiston.a()];

            block.a(iblockaccess, i, j, k);
            float f = tileentitypiston.a(0.0F);

            if (tileentitypiston.c()) {
                f = 1.0F - f;
            }

            int l = tileentitypiston.d();

            this.minX = block.minX - (double) ((float) PistonBlockTextures.b[l] * f);
            this.minY = block.minY - (double) ((float) PistonBlockTextures.c[l] * f);
            this.minZ = block.minZ - (double) ((float) PistonBlockTextures.d[l] * f);
            this.maxX = block.maxX - (double) ((float) PistonBlockTextures.b[l] * f);
            this.maxY = block.maxY - (double) ((float) PistonBlockTextures.c[l] * f);
            this.maxZ = block.maxZ - (double) ((float) PistonBlockTextures.d[l] * f);
        }
    }

    public AxisAlignedBB a(World world, int i, int j, int k, int l, float f, int i1) {
        AxisAlignedBB axisalignedbb = Block.byId[l].d(world, i, j, k);

        if (axisalignedbb == null) {
            return null;
        } else {
            axisalignedbb.a -= (double) ((float) PistonBlockTextures.b[i1] * f);
            axisalignedbb.d -= (double) ((float) PistonBlockTextures.b[i1] * f);
            axisalignedbb.b -= (double) ((float) PistonBlockTextures.c[i1] * f);
            axisalignedbb.e -= (double) ((float) PistonBlockTextures.c[i1] * f);
            axisalignedbb.c -= (double) ((float) PistonBlockTextures.d[i1] * f);
            axisalignedbb.f -= (double) ((float) PistonBlockTextures.d[i1] * f);
            return axisalignedbb;
        }
    }

    private TileEntityPiston b(IBlockAccess iblockaccess, int i, int j, int k) {
        TileEntityPiston tileentitypiston = (TileEntityPiston) iblockaccess.getTileEntity(i, j, k);

        return tileentitypiston;
    }
}

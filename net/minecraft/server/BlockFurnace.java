package net.minecraft.server;

import java.util.Random;

public class BlockFurnace extends BlockContainer {

    private final boolean a;

    protected BlockFurnace(int i, boolean flag) {
        super(i, Material.d);
        this.a = flag;
        this.bh = 45;
    }

    public int a(int i, Random random) {
        return Block.FURNACE.bi;
    }

    public void e(World world, int i, int j, int k) {
        super.e(world, i, j, k);
        this.g(world, i, j, k);
    }

    private void g(World world, int i, int j, int k) {
        int l = world.a(i, j, k - 1);
        int i1 = world.a(i, j, k + 1);
        int j1 = world.a(i - 1, j, k);
        int k1 = world.a(i + 1, j, k);
        byte b0 = 3;

        if (Block.o[l] && !Block.o[i1]) {
            b0 = 3;
        }

        if (Block.o[i1] && !Block.o[l]) {
            b0 = 2;
        }

        if (Block.o[j1] && !Block.o[k1]) {
            b0 = 5;
        }

        if (Block.o[k1] && !Block.o[j1]) {
            b0 = 4;
        }

        world.c(i, j, k, b0);
    }

    public int a(int i) {
        return i == 1 ? this.bh + 17 : (i == 0 ? this.bh + 17 : (i == 3 ? this.bh - 1 : this.bh));
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        if (world.z) {
            return true;
        } else {
            TileEntityFurnace tileentityfurnace = (TileEntityFurnace) world.m(i, j, k);

            entityhuman.a(tileentityfurnace);
            return true;
        }
    }

    public static void a(boolean flag, World world, int i, int j, int k) {
        int l = world.b(i, j, k);
        TileEntity tileentity = world.m(i, j, k);

        if (flag) {
            world.e(i, j, k, Block.BURNING_FURNACE.bi);
        } else {
            world.e(i, j, k, Block.FURNACE.bi);
        }

        world.c(i, j, k, l);
        world.a(i, j, k, tileentity);
    }

    protected TileEntity a_() {
        return new TileEntityFurnace();
    }

    public void a(World world, int i, int j, int k, EntityLiving entityliving) {
        int l = MathHelper.b((double) (entityliving.v * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            world.c(i, j, k, 2);
        }

        if (l == 1) {
            world.c(i, j, k, 5);
        }

        if (l == 2) {
            world.c(i, j, k, 3);
        }

        if (l == 3) {
            world.c(i, j, k, 4);
        }
    }
}

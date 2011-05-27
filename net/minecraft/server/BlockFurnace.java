package net.minecraft.server;

import java.util.Random;

public class BlockFurnace extends BlockContainer {

    private final boolean a;

    protected BlockFurnace(int i, boolean flag) {
        super(i, Material.d);
        this.a = flag;
        this.bb = 45;
    }

    public int a(int i, Random random) {
        return Block.FURNACE.bc;
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

        if (Block.p[l] && !Block.p[i1]) {
            b0 = 3;
        }

        if (Block.p[i1] && !Block.p[l]) {
            b0 = 2;
        }

        if (Block.p[j1] && !Block.p[k1]) {
            b0 = 5;
        }

        if (Block.p[k1] && !Block.p[j1]) {
            b0 = 4;
        }

        world.b(i, j, k, b0);
    }

    public int a(int i) {
        return i == 1 ? Block.STONE.bc : (i == 0 ? Block.STONE.bc : (i == 3 ? this.bb - 1 : this.bb));
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        TileEntityFurnace tileentityfurnace = (TileEntityFurnace) world.k(i, j, k);

        entityhuman.a(tileentityfurnace);
        return true;
    }

    public static void a(boolean flag, World world, int i, int j, int k) {
        int l = world.b(i, j, k);
        TileEntity tileentity = world.k(i, j, k);

        if (flag) {
            world.d(i, j, k, Block.BURNING_FURNACE.bc);
        } else {
            world.d(i, j, k, Block.FURNACE.bc);
        }

        world.b(i, j, k, l);
        world.a(i, j, k, tileentity);
    }

    protected TileEntity a_() {
        return new TileEntityFurnace();
    }
}

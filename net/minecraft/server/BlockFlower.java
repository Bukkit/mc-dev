package net.minecraft.server;

import java.util.Random;

public class BlockFlower extends Block {

    protected BlockFlower(int i, Material material) {
        super(i, material);
        this.b(true);
        float f = 0.2F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
        this.a(CreativeModeTab.c);
    }

    protected BlockFlower(int i) {
        this(i, Material.PLANT);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return super.canPlace(world, i, j, k) && this.f_(world.getTypeId(i, j - 1, k));
    }

    protected boolean f_(int i) {
        return i == Block.GRASS.id || i == Block.DIRT.id || i == Block.SOIL.id;
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        super.doPhysics(world, i, j, k, l);
        this.e(world, i, j, k);
    }

    public void a(World world, int i, int j, int k, Random random) {
        this.e(world, i, j, k);
    }

    protected final void e(World world, int i, int j, int k) {
        if (!this.f(world, i, j, k)) {
            this.c(world, i, j, k, world.getData(i, j, k), 0);
            world.setAir(i, j, k);
        }
    }

    public boolean f(World world, int i, int j, int k) {
        return (world.m(i, j, k) >= 8 || world.l(i, j, k)) && this.f_(world.getTypeId(i, j - 1, k));
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
        return 1;
    }
}

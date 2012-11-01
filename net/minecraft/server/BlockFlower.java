package net.minecraft.server;

import java.util.Random;

public class BlockFlower extends Block {

    protected BlockFlower(int i, int j, Material material) {
        super(i, material);
        this.textureId = j;
        this.b(true);
        float f = 0.2F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
        this.a(CreativeModeTab.c);
    }

    protected BlockFlower(int i, int j) {
        this(i, j, Material.PLANT);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return super.canPlace(world, i, j, k) && this.d_(world.getTypeId(i, j - 1, k));
    }

    protected boolean d_(int i) {
        return i == Block.GRASS.id || i == Block.DIRT.id || i == Block.SOIL.id;
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        super.doPhysics(world, i, j, k, l);
        this.c(world, i, j, k);
    }

    public void b(World world, int i, int j, int k, Random random) {
        this.c(world, i, j, k);
    }

    protected final void c(World world, int i, int j, int k) {
        if (!this.d(world, i, j, k)) {
            this.c(world, i, j, k, world.getData(i, j, k), 0);
            world.setTypeId(i, j, k, 0);
        }
    }

    public boolean d(World world, int i, int j, int k) {
        return (world.l(i, j, k) >= 8 || world.k(i, j, k)) && this.d_(world.getTypeId(i, j - 1, k));
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
        return 1;
    }
}

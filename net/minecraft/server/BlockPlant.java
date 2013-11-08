package net.minecraft.server;

import java.util.Random;

public class BlockPlant extends Block {

    protected BlockPlant(Material material) {
        super(material);
        this.a(true);
        float f = 0.2F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
        this.a(CreativeModeTab.c);
    }

    protected BlockPlant() {
        this(Material.PLANT);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return super.canPlace(world, i, j, k) && this.a(world.getType(i, j - 1, k));
    }

    protected boolean a(Block block) {
        return block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.SOIL;
    }

    public void doPhysics(World world, int i, int j, int k, Block block) {
        super.doPhysics(world, i, j, k, block);
        this.e(world, i, j, k);
    }

    public void a(World world, int i, int j, int k, Random random) {
        this.e(world, i, j, k);
    }

    protected void e(World world, int i, int j, int k) {
        if (!this.j(world, i, j, k)) {
            this.b(world, i, j, k, world.getData(i, j, k), 0);
            world.setTypeAndData(i, j, k, e(0), 0, 2);
        }
    }

    public boolean j(World world, int i, int j, int k) {
        return this.a(world.getType(i, j - 1, k));
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
        return 1;
    }
}

package net.minecraft.server;

public class BlockWaterLily extends BlockFlower {

    protected BlockWaterLily(int i, int j) {
        super(i, j);
        float f = 0.5F;
        float f1 = 0.015625F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    }

    public int c() {
        return 23;
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        return AxisAlignedBB.b((double) i + this.minX, (double) j + this.minY, (double) k + this.minZ, (double) i + this.maxX, (double) j + this.maxY, (double) k + this.maxZ);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return super.canPlace(world, i, j, k);
    }

    protected boolean d(int i) {
        return i == Block.STATIONARY_WATER.id;
    }

    public boolean f(World world, int i, int j, int k) {
        return j >= 0 && j < world.height ? world.getMaterial(i, j - 1, k) == Material.WATER && world.getData(i, j - 1, k) == 0 : false;
    }
}

package net.minecraft.server;

import java.util.List;

public class BlockWaterLily extends BlockFlower {

    protected BlockWaterLily(int i) {
        super(i);
        float f = 0.5F;
        float f1 = 0.015625F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
        this.a(CreativeModeTab.c);
    }

    public int d() {
        return 23;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        if (entity == null || !(entity instanceof EntityBoat)) {
            super.a(world, i, j, k, axisalignedbb, list, entity);
        }
    }

    public AxisAlignedBB b(World world, int i, int j, int k) {
        return AxisAlignedBB.a().a((double) i + this.minX, (double) j + this.minY, (double) k + this.minZ, (double) i + this.maxX, (double) j + this.maxY, (double) k + this.maxZ);
    }

    protected boolean f_(int i) {
        return i == Block.STATIONARY_WATER.id;
    }

    public boolean f(World world, int i, int j, int k) {
        return j >= 0 && j < 256 ? world.getMaterial(i, j - 1, k) == Material.WATER && world.getData(i, j - 1, k) == 0 : false;
    }
}

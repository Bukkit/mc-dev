package net.minecraft.server;

import java.util.List;

public class BlockWaterLily extends BlockPlant {

    protected BlockWaterLily() {
        float f = 0.5F;
        float f1 = 0.015625F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
        this.a(CreativeModeTab.c);
    }

    public int b() {
        return 23;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        if (entity == null || !(entity instanceof EntityBoat)) {
            super.a(world, i, j, k, axisalignedbb, list, entity);
        }
    }

    public AxisAlignedBB a(World world, int i, int j, int k) {
        return AxisAlignedBB.a().a((double) i + this.minX, (double) j + this.minY, (double) k + this.minZ, (double) i + this.maxX, (double) j + this.maxY, (double) k + this.maxZ);
    }

    protected boolean a(Block block) {
        return block == Blocks.STATIONARY_WATER;
    }

    public boolean j(World world, int i, int j, int k) {
        return j >= 0 && j < 256 ? world.getType(i, j - 1, k).getMaterial() == Material.WATER && world.getData(i, j - 1, k) == 0 : false;
    }
}

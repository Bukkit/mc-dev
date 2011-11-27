package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class BlockEnderPortal extends BlockContainer {

    public static boolean a = false;

    protected BlockEnderPortal(int i, Material material) {
        super(i, 0, material);
        this.a(1.0F);
    }

    public TileEntity a_() {
        return new TileEntityEnderPortal();
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        float f = 0.0625F;

        this.a(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist) {}

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int a(Random random) {
        return 0;
    }

    public void a(World world, int i, int j, int k, Entity entity) {
        if (entity.vehicle == null && entity.passenger == null && entity instanceof EntityHuman && !world.isStatic) {
            ((EntityHuman) entity).e(1);
        }
    }

    public int c() {
        return -1;
    }

    public void onPlace(World world, int i, int j, int k) {
        if (!a) {
            if (world.worldProvider.dimension != 0) {
                world.setTypeId(i, j, k, 0);
            }
        }
    }
}

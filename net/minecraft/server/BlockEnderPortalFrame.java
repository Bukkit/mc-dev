package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockEnderPortalFrame extends Block {

    public BlockEnderPortalFrame(int i) {
        super(i, Material.STONE);
    }

    public boolean c() {
        return false;
    }

    public int d() {
        return 26;
    }

    public void g() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
        super.a(world, i, j, k, axisalignedbb, list, entity);
        int l = world.getData(i, j, k);

        if (d(l)) {
            this.a(0.3125F, 0.8125F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
        }

        this.g();
    }

    public static boolean d(int i) {
        return (i & 4) != 0;
    }

    public int getDropType(int i, Random random, int j) {
        return 0;
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemstack) {
        int l = ((MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;

        world.setData(i, j, k, l, 2);
    }

    public boolean q_() {
        return true;
    }

    public int b_(World world, int i, int j, int k, int l) {
        int i1 = world.getData(i, j, k);

        return d(i1) ? 15 : 0;
    }
}

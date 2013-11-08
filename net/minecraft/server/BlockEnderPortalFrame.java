package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockEnderPortalFrame extends Block {

    public BlockEnderPortalFrame() {
        super(Material.STONE);
    }

    public boolean c() {
        return false;
    }

    public int b() {
        return 26;
    }

    public void g() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
        super.a(world, i, j, k, axisalignedbb, list, entity);
        int l = world.getData(i, j, k);

        if (b(l)) {
            this.a(0.3125F, 0.8125F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
        }

        this.g();
    }

    public static boolean b(int i) {
        return (i & 4) != 0;
    }

    public Item getDropType(int i, Random random, int j) {
        return null;
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemstack) {
        int l = ((MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;

        world.setData(i, j, k, l, 2);
    }

    public boolean M() {
        return true;
    }

    public int g(World world, int i, int j, int k, int l) {
        int i1 = world.getData(i, j, k);

        return b(i1) ? 15 : 0;
    }
}

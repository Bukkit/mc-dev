package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockEnderPortalFrame extends Block {

    public BlockEnderPortalFrame(int i) {
        super(i, 159, Material.STONE);
    }

    public int a(int i, int j) {
        return i == 1 ? this.textureId - 1 : (i == 0 ? this.textureId + 16 : this.textureId);
    }

    public boolean c() {
        return false;
    }

    public int d() {
        return 26;
    }

    public void f() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
        super.a(world, i, j, k, axisalignedbb, list, entity);
        int l = world.getData(i, j, k);

        if (e(l)) {
            this.a(0.3125F, 0.8125F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
        }

        this.f();
    }

    public static boolean e(int i) {
        return (i & 4) != 0;
    }

    public int getDropType(int i, Random random, int j) {
        return 0;
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
        int l = ((MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;

        world.setData(i, j, k, l);
    }
}

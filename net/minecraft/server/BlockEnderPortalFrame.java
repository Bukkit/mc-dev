package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class BlockEnderPortalFrame extends Block {

    public BlockEnderPortalFrame(int i) {
        super(i, 159, Material.SHATTERABLE);
    }

    public int a(int i, int j) {
        return i == 1 ? this.textureId - 1 : (i == 0 ? this.textureId + 16 : this.textureId);
    }

    public boolean a() {
        return false;
    }

    public int c() {
        return 26;
    }

    public void f() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
        super.a(world, i, j, k, axisalignedbb, arraylist);
        int l = world.getData(i, j, k);

        if (d(l)) {
            this.a(0.3125F, 0.8125F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
            super.a(world, i, j, k, axisalignedbb, arraylist);
        }

        this.f();
    }

    public static boolean d(int i) {
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

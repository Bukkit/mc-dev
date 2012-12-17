package net.minecraft.server;

import java.util.Random;

public class BlockCake extends Block {

    protected BlockCake(int i, int j) {
        super(i, j, Material.CAKE);
        this.b(true);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.getData(i, j, k);
        float f = 0.0625F;
        float f1 = (float) (1 + l * 2) / 16.0F;
        float f2 = 0.5F;

        this.a(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
    }

    public void f() {
        float f = 0.0625F;
        float f1 = 0.5F;

        this.a(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        int l = world.getData(i, j, k);
        float f = 0.0625F;
        float f1 = (float) (1 + l * 2) / 16.0F;
        float f2 = 0.5F;

        return AxisAlignedBB.a().a((double) ((float) i + f1), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) ((float) j + f2 - f), (double) ((float) (k + 1) - f));
    }

    public int a(int i, int j) {
        return i == 1 ? this.textureId : (i == 0 ? this.textureId + 3 : (j > 0 && i == 4 ? this.textureId + 2 : this.textureId + 1));
    }

    public int a(int i) {
        return i == 1 ? this.textureId : (i == 0 ? this.textureId + 3 : this.textureId + 1);
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        this.b(world, i, j, k, entityhuman);
        return true;
    }

    public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
        this.b(world, i, j, k, entityhuman);
    }

    private void b(World world, int i, int j, int k, EntityHuman entityhuman) {
        if (entityhuman.g(false)) {
            entityhuman.getFoodData().eat(2, 0.1F);
            int l = world.getData(i, j, k) + 1;

            if (l >= 6) {
                world.setTypeId(i, j, k, 0);
            } else {
                world.setData(i, j, k, l);
                world.j(i, j, k);
            }
        }
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return !super.canPlace(world, i, j, k) ? false : this.d(world, i, j, k);
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (!this.d(world, i, j, k)) {
            this.c(world, i, j, k, world.getData(i, j, k), 0);
            world.setTypeId(i, j, k, 0);
        }
    }

    public boolean d(World world, int i, int j, int k) {
        return world.getMaterial(i, j - 1, k).isBuildable();
    }

    public int a(Random random) {
        return 0;
    }

    public int getDropType(int i, Random random, int j) {
        return 0;
    }
}

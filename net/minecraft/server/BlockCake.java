package net.minecraft.server;

import java.util.Random;

public class BlockCake extends Block {

    protected BlockCake(int i, int j) {
        super(i, j, Material.CAKE);
        this.a(true);
    }

    public void a(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.getData(i, j, k);
        float f = 0.0625F;
        float f1 = (float) (1 + l * 2) / 16.0F;
        float f2 = 0.5F;

        this.a(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        int l = world.getData(i, j, k);
        float f = 0.0625F;
        float f1 = (float) (1 + l * 2) / 16.0F;
        float f2 = 0.5F;

        return AxisAlignedBB.b((double) ((float) i + f1), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) ((float) j + f2 - f), (double) ((float) (k + 1) - f));
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

    public boolean a() {
        return false;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
        this.c(world, i, j, k, entityhuman);
        return true;
    }

    public void b(World world, int i, int j, int k, EntityHuman entityhuman) {
        this.c(world, i, j, k, entityhuman);
    }

    private void c(World world, int i, int j, int k, EntityHuman entityhuman) {
        if (entityhuman.health < 20) {
            entityhuman.b(3);
            int l = world.getData(i, j, k) + 1;

            if (l >= 6) {
                world.setTypeId(i, j, k, 0);
            } else {
                world.setData(i, j, k, l);
                world.i(i, j, k);
            }
        }
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return !super.canPlace(world, i, j, k) ? false : this.f(world, i, j, k);
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (!this.f(world, i, j, k)) {
            this.g(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, 0);
        }
    }

    public boolean f(World world, int i, int j, int k) {
        return world.getMaterial(i, j - 1, k).isBuildable();
    }

    public int a(Random random) {
        return 0;
    }

    public int a(int i, Random random) {
        return 0;
    }
}

package net.minecraft.server;

import java.util.Random;

public class BlockSnow extends Block {

    protected BlockSnow(int i, int j) {
        super(i, j, Material.s);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.a(true);
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return null;
    }

    public boolean a() {
        return false;
    }

    public boolean a(World world, int i, int j, int k) {
        int l = world.a(i, j - 1, k);

        return l != 0 && Block.m[l].a() ? world.c(i, j - 1, k).c() : false;
    }

    public void b(World world, int i, int j, int k, int l) {
        this.g(world, i, j, k);
    }

    private boolean g(World world, int i, int j, int k) {
        if (!this.a(world, i, j, k)) {
            this.a_(world, i, j, k, world.b(i, j, k));
            world.e(i, j, k, 0);
            return false;
        } else {
            return true;
        }
    }

    public void g(World world, int i, int j, int k, int l) {
        int i1 = Item.SNOW_BALL.ba;
        float f = 0.7F;
        double d0 = (double) (world.l.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
        double d1 = (double) (world.l.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
        double d2 = (double) (world.l.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
        EntityItem entityitem = new EntityItem(world, (double) i + d0, (double) j + d1, (double) k + d2, new ItemStack(i1, 1, 0));

        entityitem.c = 10;
        world.a((Entity) entityitem);
        world.e(i, j, k, 0);
    }

    public int a(int i, Random random) {
        return Item.SNOW_BALL.ba;
    }

    public int a(Random random) {
        return 0;
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (world.a(EnumSkyBlock.BLOCK, i, j, k) > 11) {
            this.a_(world, i, j, k, world.b(i, j, k));
            world.e(i, j, k, 0);
        }
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        Material material = iblockaccess.c(i, j, k);

        return l == 1 ? true : (material == this.bt ? false : super.a(iblockaccess, i, j, k, l));
    }
}

package net.minecraft.server;

import java.util.Random;

public class BlockDiode extends Block {

    public static final double[] a = new double[] { -0.0625D, 0.0625D, 0.1875D, 0.3125D};
    private static final int[] b = new int[] { 1, 2, 3, 4};
    private final boolean c;

    protected BlockDiode(int i, boolean flag) {
        super(i, 102, Material.ORIENTABLE);
        this.c = flag;
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    public boolean a(World world, int i, int j, int k) {
        return !world.d(i, j - 1, k) ? false : super.a(world, i, j, k);
    }

    public boolean f(World world, int i, int j, int k) {
        return !world.d(i, j - 1, k) ? false : super.f(world, i, j, k);
    }

    public void a(World world, int i, int j, int k, Random random) {
        int l = world.getData(i, j, k);
        boolean flag = this.g(world, i, j, k, l);

        if (this.c && !flag) {
            world.b(i, j, k, Block.DIODE_OFF.id, l);
        } else if (!this.c) {
            world.b(i, j, k, Block.DIODE_ON.id, l);
            if (!flag) {
                int i1 = (l & 12) >> 2;

                world.c(i, j, k, Block.DIODE_ON.id, b[i1] * 2);
            }
        }
    }

    public int a(int i, int j) {
        return i == 0 ? (this.c ? 99 : 115) : (i == 1 ? (this.c ? 147 : 131) : 5);
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return l != 0 && l != 1;
    }

    public int a(int i) {
        return this.a(i, 0);
    }

    public boolean c(World world, int i, int j, int k, int l) {
        return this.b(world, i, j, k, l);
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        if (!this.c) {
            return false;
        } else {
            int i1 = iblockaccess.getData(i, j, k) & 3;

            return i1 == 0 && l == 3 ? true : (i1 == 1 && l == 4 ? true : (i1 == 2 && l == 2 ? true : i1 == 3 && l == 5));
        }
    }

    public void a(World world, int i, int j, int k, int l) {
        if (!this.f(world, i, j, k)) {
            this.b_(world, i, j, k, world.getData(i, j, k));
            world.e(i, j, k, 0);
        } else {
            int i1 = world.getData(i, j, k);
            boolean flag = this.g(world, i, j, k, i1);
            int j1 = (i1 & 12) >> 2;

            if (this.c && !flag) {
                world.c(i, j, k, this.id, b[j1] * 2);
            } else if (!this.c && flag) {
                world.c(i, j, k, this.id, b[j1] * 2);
            }
        }
    }

    private boolean g(World world, int i, int j, int k, int l) {
        int i1 = l & 3;

        switch (i1) {
        case 0:
            return world.j(i, j, k + 1, 3);

        case 1:
            return world.j(i - 1, j, k, 4);

        case 2:
            return world.j(i, j, k - 1, 2);

        case 3:
            return world.j(i + 1, j, k, 5);

        default:
            return false;
        }
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        int l = world.getData(i, j, k);
        int i1 = (l & 12) >> 2;

        i1 = i1 + 1 << 2 & 12;
        world.c(i, j, k, i1 | l & 3);
        return true;
    }

    public boolean c() {
        return false;
    }

    public void a(World world, int i, int j, int k, EntityLiving entityliving) {
        int l = ((MathHelper.b((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;

        world.c(i, j, k, l);
        boolean flag = this.g(world, i, j, k, l);

        if (flag) {
            world.c(i, j, k, this.id, 1);
        }
    }

    public void e(World world, int i, int j, int k) {
        world.h(i + 1, j, k, this.id);
        world.h(i - 1, j, k, this.id);
        world.h(i, j, k + 1, this.id);
        world.h(i, j, k - 1, this.id);
        world.h(i, j - 1, k, this.id);
        world.h(i, j + 1, k, this.id);
    }

    public boolean a() {
        return false;
    }

    public int a(int i, Random random) {
        return Item.DIODE.id;
    }
}

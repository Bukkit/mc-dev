package net.minecraft.server;

import java.util.Random;

public class BlockLadder extends Block {

    protected BlockLadder(int i, int j) {
        super(i, j, Material.n);
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        int l = world.b(i, j, k);
        float f = 0.125F;

        if (l == 2) {
            this.a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        }

        if (l == 3) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        }

        if (l == 4) {
            this.a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (l == 5) {
            this.a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        }

        return super.d(world, i, j, k);
    }

    public boolean a() {
        return false;
    }

    public boolean a(World world, int i, int j, int k) {
        return world.d(i - 1, j, k) ? true : (world.d(i + 1, j, k) ? true : (world.d(i, j, k - 1) ? true : world.d(i, j, k + 1)));
    }

    public void c(World world, int i, int j, int k, int l) {
        int i1 = world.b(i, j, k);

        if ((i1 == 0 || l == 2) && world.d(i, j, k + 1)) {
            i1 = 2;
        }

        if ((i1 == 0 || l == 3) && world.d(i, j, k - 1)) {
            i1 = 3;
        }

        if ((i1 == 0 || l == 4) && world.d(i + 1, j, k)) {
            i1 = 4;
        }

        if ((i1 == 0 || l == 5) && world.d(i - 1, j, k)) {
            i1 = 5;
        }

        world.b(i, j, k, i1);
    }

    public void b(World world, int i, int j, int k, int l) {
        int i1 = world.b(i, j, k);
        boolean flag = false;

        if (i1 == 2 && world.d(i, j, k + 1)) {
            flag = true;
        }

        if (i1 == 3 && world.d(i, j, k - 1)) {
            flag = true;
        }

        if (i1 == 4 && world.d(i + 1, j, k)) {
            flag = true;
        }

        if (i1 == 5 && world.d(i - 1, j, k)) {
            flag = true;
        }

        if (!flag) {
            this.a_(world, i, j, k, i1);
            world.d(i, j, k, 0);
        }

        super.b(world, i, j, k, l);
    }

    public int a(Random random) {
        return 1;
    }
}

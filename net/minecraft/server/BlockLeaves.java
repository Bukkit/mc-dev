package net.minecraft.server;

import java.util.Random;

public class BlockLeaves extends BlockLeavesBase {

    private int b;
    private int c = 0;

    protected BlockLeaves(int i, int j) {
        super(i, j, Material.h, false);
        this.b = j;
        this.a(true);
    }

    public void b(World world, int i, int j, int k, int l) {
        this.c = 0;
        this.g(world, i, j, k);
        super.b(world, i, j, k, l);
    }

    public void e(World world, int i, int j, int k, int l) {
        if (world.a(i, j, k) == this.bc) {
            int i1 = world.b(i, j, k);

            if (i1 != 0 && i1 == l - 1) {
                this.g(world, i, j, k);
            }
        }
    }

    public void g(World world, int i, int j, int k) {
        if (this.c++ < 100) {
            int l = world.c(i, j - 1, k).a() ? 16 : 0;
            int i1 = world.b(i, j, k);

            if (i1 == 0) {
                i1 = 1;
                world.b(i, j, k, 1);
            }

            l = this.f(world, i, j - 1, k, l);
            l = this.f(world, i, j, k - 1, l);
            l = this.f(world, i, j, k + 1, l);
            l = this.f(world, i - 1, j, k, l);
            l = this.f(world, i + 1, j, k, l);
            int j1 = l - 1;

            if (j1 < 10) {
                j1 = 1;
            }

            if (j1 != i1) {
                world.b(i, j, k, j1);
                this.e(world, i, j - 1, k, i1);
                this.e(world, i, j + 1, k, i1);
                this.e(world, i, j, k - 1, i1);
                this.e(world, i, j, k + 1, i1);
                this.e(world, i - 1, j, k, i1);
                this.e(world, i + 1, j, k, i1);
            }
        }
    }

    private int f(World world, int i, int j, int k, int l) {
        int i1 = world.a(i, j, k);

        if (i1 == Block.LOG.bc) {
            return 16;
        } else {
            if (i1 == this.bc) {
                int j1 = world.b(i, j, k);

                if (j1 != 0 && j1 > l) {
                    return j1;
                }
            }

            return l;
        }
    }

    public void a(World world, int i, int j, int k, Random random) {
        int l = world.b(i, j, k);

        if (l == 0) {
            this.c = 0;
            this.g(world, i, j, k);
        } else if (l == 1) {
            this.h(world, i, j, k);
        } else if (random.nextInt(10) == 0) {
            this.g(world, i, j, k);
        }
    }

    private void h(World world, int i, int j, int k) {
        this.a_(world, i, j, k, world.b(i, j, k));
        world.d(i, j, k, 0);
    }

    public int a(Random random) {
        return random.nextInt(20) == 0 ? 1 : 0;
    }

    public int a(int i, Random random) {
        return Block.SAPLING.bc;
    }

    public boolean b() {
        return !this.a;
    }

    public void b(World world, int i, int j, int k, Entity entity) {
        super.b(world, i, j, k, entity);
    }
}

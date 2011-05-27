package net.minecraft.server;

import java.util.Random;

public class BlockSoil extends Block {

    protected BlockSoil(int i) {
        super(i, Material.b);
        this.bh = 87;
        this.a(true);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.c(255);
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return AxisAlignedBB.b((double) (i + 0), (double) (j + 0), (double) (k + 0), (double) (i + 1), (double) (j + 1), (double) (k + 1));
    }

    public boolean a() {
        return false;
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (random.nextInt(5) == 0) {
            if (this.h(world, i, j, k)) {
                world.b(i, j, k, 7);
            } else {
                int l = world.b(i, j, k);

                if (l > 0) {
                    world.b(i, j, k, l - 1);
                } else if (!this.g(world, i, j, k)) {
                    world.d(i, j, k, Block.DIRT.bi);
                }
            }
        }
    }

    public void b(World world, int i, int j, int k, Entity entity) {
        if (world.l.nextInt(4) == 0) {
            world.d(i, j, k, Block.DIRT.bi);
        }
    }

    private boolean g(World world, int i, int j, int k) {
        byte b0 = 0;

        for (int l = i - b0; l <= i + b0; ++l) {
            for (int i1 = k - b0; i1 <= k + b0; ++i1) {
                if (world.a(l, j + 1, i1) == Block.CROPS.bi) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean h(World world, int i, int j, int k) {
        for (int l = i - 4; l <= i + 4; ++l) {
            for (int i1 = j; i1 <= j + 1; ++i1) {
                for (int j1 = k - 4; j1 <= k + 4; ++j1) {
                    if (world.c(l, i1, j1) == Material.f) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void b(World world, int i, int j, int k, int l) {
        super.b(world, i, j, k, l);
        Material material = world.c(i, j + 1, k);

        if (material.a()) {
            world.d(i, j, k, Block.DIRT.bi);
        }
    }

    public int a(int i, Random random) {
        return Block.DIRT.a(0, random);
    }
}

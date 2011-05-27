package net.minecraft.server;

import java.util.Random;

public class BlockStationary extends BlockFluids {

    protected BlockStationary(int i, Material material) {
        super(i, material);
        this.a(false);
        if (material == Material.g) {
            this.a(true);
        }
    }

    public void b(World world, int i, int j, int k, int l) {
        super.b(world, i, j, k, l);
        if (world.a(i, j, k) == this.bc) {
            this.i(world, i, j, k);
        }
    }

    private void i(World world, int i, int j, int k) {
        int l = world.b(i, j, k);

        world.h = true;
        world.a(i, j, k, this.bc - 1, l);
        world.b(i, j, k, i, j, k);
        world.h(i, j, k, this.bc - 1);
        world.h = false;
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (this.bn == Material.g) {
            int l = random.nextInt(3);

            for (int i1 = 0; i1 < l; ++i1) {
                i += random.nextInt(3) - 1;
                ++j;
                k += random.nextInt(3) - 1;
                int j1 = world.a(i, j, k);

                if (j1 == 0) {
                    if (this.j(world, i - 1, j, k) || this.j(world, i + 1, j, k) || this.j(world, i, j, k - 1) || this.j(world, i, j, k + 1) || this.j(world, i, j - 1, k) || this.j(world, i, j + 1, k)) {
                        world.d(i, j, k, Block.FIRE.bc);
                        return;
                    }
                } else if (Block.n[j1].bn.c()) {
                    return;
                }
            }
        }
    }

    private boolean j(World world, int i, int j, int k) {
        return world.c(i, j, k).e();
    }
}

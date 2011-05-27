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
        int i = world.b(i, j, k);

        world.h = true;
        world.a(i, j, k, this.bc - 1, i);
        world.b(i, j, k, i, j, k);
        world.h(i, j, k, this.bc - 1);
        world.h = false;
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (this.bn == Material.g) {
            int i = random.nextInt(3);

            for (int j = 0; j < i; ++j) {
                i += random.nextInt(3) - 1;
                ++j;
                k += random.nextInt(3) - 1;
                int k = world.a(i, j, k);

                if (k == 0) {
                    if (this.j(world, i - 1, j, k) || this.j(world, i + 1, j, k) || this.j(world, i, j, k - 1) || this.j(world, i, j, k + 1) || this.j(world, i, j - 1, k) || this.j(world, i, j + 1, k)) {
                        return;
                    }
                } else if (Block.n[k].bn.c()) {
                    return;
                }
            }
        }
    }

    private boolean j(World world, int i, int j, int k) {
        return world.c(i, j, k).e();
    }
}

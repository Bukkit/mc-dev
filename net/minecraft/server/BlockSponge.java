package net.minecraft.server;

public class BlockSponge extends Block {

    protected BlockSponge(int i) {
        super(i, Material.j);
        this.bh = 48;
    }

    public void e(World world, int i, int j, int k) {
        byte b0 = 2;

        for (int l = i - b0; l <= i + b0; ++l) {
            for (int i1 = j - b0; i1 <= j + b0; ++i1) {
                for (int j1 = k - b0; j1 <= k + b0; ++j1) {
                    if (world.c(l, i1, j1) == Material.f) {
                        ;
                    }
                }
            }
        }
    }

    public void b(World world, int i, int j, int k) {
        byte b0 = 2;

        for (int l = i - b0; l <= i + b0; ++l) {
            for (int i1 = j - b0; i1 <= j + b0; ++i1) {
                for (int j1 = k - b0; j1 <= k + b0; ++j1) {
                    world.g(l, i1, j1, world.a(l, i1, j1));
                }
            }
        }
    }
}

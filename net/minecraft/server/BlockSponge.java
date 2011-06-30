package net.minecraft.server;

public class BlockSponge extends Block {

    protected BlockSponge(int i) {
        super(i, Material.SPONGE);
        this.textureId = 48;
    }

    public void c(World world, int i, int j, int k) {
        byte b0 = 2;

        for (int l = i - b0; l <= i + b0; ++l) {
            for (int i1 = j - b0; i1 <= j + b0; ++i1) {
                for (int j1 = k - b0; j1 <= k + b0; ++j1) {
                    if (world.getMaterial(l, i1, j1) == Material.WATER) {
                        ;
                    }
                }
            }
        }
    }

    public void remove(World world, int i, int j, int k) {
        byte b0 = 2;

        for (int l = i - b0; l <= i + b0; ++l) {
            for (int i1 = j - b0; i1 <= j + b0; ++i1) {
                for (int j1 = k - b0; j1 <= k + b0; ++j1) {
                    world.applyPhysics(l, i1, j1, world.getTypeId(l, i1, j1));
                }
            }
        }
    }
}

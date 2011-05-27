package net.minecraft.server;

public class BlockMushroom extends BlockFlower {

    protected BlockMushroom(int i, int j) {
        super(i, j);
        float f = 0.2F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    protected boolean c(int i) {
        return Block.o[i];
    }

    public boolean f(World world, int i, int j, int k) {
        return world.j(i, j, k) <= 13 && this.c(world.getTypeId(i, j - 1, k));
    }
}

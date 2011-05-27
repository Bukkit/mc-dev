package net.minecraft.server;

public class BlockMushroom extends BlockFlower {

    protected BlockMushroom(int i, int j) {
        super(i, j);
        float f = 0.2F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    protected boolean b(int i) {
        return Block.p[i];
    }

    public boolean f(World world, int i, int j, int k) {
        return world.h(i, j, k) <= 13 && this.b(world.a(i, j - 1, k));
    }
}

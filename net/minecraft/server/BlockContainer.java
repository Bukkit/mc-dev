package net.minecraft.server;

public abstract class BlockContainer extends Block {

    protected BlockContainer(int i, Material material) {
        super(i, material);
        q[i] = true;
    }

    protected BlockContainer(int i, int j, Material material) {
        super(i, j, material);
    }

    public void e(World world, int i, int j, int k) {
        super.e(world, i, j, k);
        world.a(i, j, k, this.a_());
    }

    public void b(World world, int i, int j, int k) {
        super.b(world, i, j, k);
        world.l(i, j, k);
    }

    protected abstract TileEntity a_();
}

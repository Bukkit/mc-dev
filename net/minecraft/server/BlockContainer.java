package net.minecraft.server;

public abstract class BlockContainer extends Block {

    protected BlockContainer(int i, Material material) {
        super(i, material);
        isTileEntity[i] = true;
    }

    protected BlockContainer(int i, int j, Material material) {
        super(i, j, material);
        isTileEntity[i] = true;
    }

    public void c(World world, int i, int j, int k) {
        super.c(world, i, j, k);
        world.setTileEntity(i, j, k, this.a_());
    }

    public void remove(World world, int i, int j, int k) {
        super.remove(world, i, j, k);
        world.o(i, j, k);
    }

    protected abstract TileEntity a_();
}

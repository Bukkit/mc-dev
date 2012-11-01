package net.minecraft.server;

public abstract class BlockContainer extends Block {

    protected BlockContainer(int i, Material material) {
        super(i, material);
        this.isTileEntity = true;
    }

    protected BlockContainer(int i, int j, Material material) {
        super(i, j, material);
        this.isTileEntity = true;
    }

    public void onPlace(World world, int i, int j, int k) {
        super.onPlace(world, i, j, k);
        world.setTileEntity(i, j, k, this.a(world));
    }

    public void remove(World world, int i, int j, int k, int l, int i1) {
        super.remove(world, i, j, k, l, i1);
        world.r(i, j, k);
    }

    public abstract TileEntity a(World world);

    public void b(World world, int i, int j, int k, int l, int i1) {
        super.b(world, i, j, k, l, i1);
        TileEntity tileentity = world.getTileEntity(i, j, k);

        if (tileentity != null) {
            tileentity.b(l, i1);
        }
    }
}

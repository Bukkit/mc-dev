package net.minecraft.server;

public abstract class BlockContainer extends Block {

    protected BlockContainer(int i, Material material) {
        super(i, material);
        isTileEntity[this.id] = true;
    }

    protected BlockContainer(int i, int j, Material material) {
        super(i, j, material);
        isTileEntity[this.id] = true;
    }

    public void onPlace(World world, int i, int j, int k) {
        super.onPlace(world, i, j, k);
        world.setTileEntity(i, j, k, this.a_());
    }

    public void remove(World world, int i, int j, int k) {
        super.remove(world, i, j, k);
        world.n(i, j, k);
    }

    public abstract TileEntity a_();

    public void a(World world, int i, int j, int k, int l, int i1) {
        super.a(world, i, j, k, l, i1);
        TileEntity tileentity = world.getTileEntity(i, j, k);

        if (tileentity != null) {
            tileentity.b(l, i1);
        }
    }
}

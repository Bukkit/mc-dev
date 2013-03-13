package net.minecraft.server;

public abstract class BlockContainer extends Block implements IContainer {

    protected BlockContainer(int i, Material material) {
        super(i, material);
        this.isTileEntity = true;
    }

    public void onPlace(World world, int i, int j, int k) {
        super.onPlace(world, i, j, k);
    }

    public void remove(World world, int i, int j, int k, int l, int i1) {
        super.remove(world, i, j, k, l, i1);
        world.s(i, j, k);
    }

    public boolean b(World world, int i, int j, int k, int l, int i1) {
        super.b(world, i, j, k, l, i1);
        TileEntity tileentity = world.getTileEntity(i, j, k);

        return tileentity != null ? tileentity.b(l, i1) : false;
    }
}

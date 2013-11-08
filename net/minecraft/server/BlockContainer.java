package net.minecraft.server;

public abstract class BlockContainer extends Block implements IContainer {

    protected BlockContainer(Material material) {
        super(material);
        this.isTileEntity = true;
    }

    public void onPlace(World world, int i, int j, int k) {
        super.onPlace(world, i, j, k);
    }

    public void remove(World world, int i, int j, int k, Block block, int l) {
        super.remove(world, i, j, k, block, l);
        world.p(i, j, k);
    }

    public boolean a(World world, int i, int j, int k, int l, int i1) {
        super.a(world, i, j, k, l, i1);
        TileEntity tileentity = world.getTileEntity(i, j, k);

        return tileentity != null ? tileentity.c(l, i1) : false;
    }
}

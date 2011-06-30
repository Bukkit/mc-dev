package net.minecraft.server;

public class BlockFence extends Block {

    public BlockFence(int i, int j) {
        super(i, j, Material.WOOD);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return world.getTypeId(i, j - 1, k) == this.id ? true : (!world.getMaterial(i, j - 1, k).isBuildable() ? false : super.canPlace(world, i, j, k));
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        return AxisAlignedBB.b((double) i, (double) j, (double) k, (double) (i + 1), (double) ((float) j + 1.5F), (double) (k + 1));
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }
}

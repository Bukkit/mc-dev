package net.minecraft.server;

public class BlockWeb extends Block {

    public BlockWeb(int i, int j) {
        super(i, j, Material.CLOTH);
    }

    public void a(World world, int i, int j, int k, Entity entity) {
        entity.bb = true;
    }

    public boolean a() {
        return false;
    }

    public AxisAlignedBB d(World world, int i, int j, int k) {
        return null;
    }
}

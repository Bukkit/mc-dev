package net.minecraft.server;

public class BlockAir extends Block {

    protected BlockAir() {
        super(Material.AIR);
    }

    public int b() {
        return -1;
    }

    public AxisAlignedBB a(World world, int i, int j, int k) {
        return null;
    }

    public boolean c() {
        return false;
    }

    public boolean a(int i, boolean flag) {
        return false;
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {}
}

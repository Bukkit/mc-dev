package net.minecraft.server;

public class BlockFenceGate extends Block {

    public BlockFenceGate(int i, int j) {
        super(i, j, Material.WOOD);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return !world.getMaterial(i, j - 1, k).isBuildable() ? false : super.canPlace(world, i, j, k);
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        int l = world.getData(i, j, k);

        return d(l) ? null : AxisAlignedBB.b((double) i, (double) j, (double) k, (double) (i + 1), (double) ((float) j + 1.5F), (double) (k + 1));
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int c() {
        return 21;
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
        int l = (MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;

        world.setData(i, j, k, l);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
        int l = world.getData(i, j, k);

        if (d(l)) {
            world.setData(i, j, k, l & -5);
        } else {
            int i1 = (MathHelper.floor((double) (entityhuman.yaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;
            int j1 = e(l);

            if (j1 == (i1 + 2) % 4) {
                l = i1;
            }

            world.setData(i, j, k, l | 4);
        }

        world.a(entityhuman, 1003, i, j, k, 0);
        return true;
    }

    public static boolean d(int i) {
        return (i & 4) != 0;
    }

    public static int e(int i) {
        return i & 3;
    }
}

package net.minecraft.server;

public class BlockFenceGate extends BlockDirectional {

    public BlockFenceGate(int i) {
        super(i, Material.WOOD);
        this.a(CreativeModeTab.d);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return !world.getMaterial(i, j - 1, k).isBuildable() ? false : super.canPlace(world, i, j, k);
    }

    public AxisAlignedBB b(World world, int i, int j, int k) {
        int l = world.getData(i, j, k);

        return m_(l) ? null : (l != 2 && l != 0 ? AxisAlignedBB.a().a((double) ((float) i + 0.375F), (double) j, (double) k, (double) ((float) i + 0.625F), (double) ((float) j + 1.5F), (double) (k + 1)) : AxisAlignedBB.a().a((double) i, (double) j, (double) ((float) k + 0.375F), (double) (i + 1), (double) ((float) j + 1.5F), (double) ((float) k + 0.625F)));
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = j(iblockaccess.getData(i, j, k));

        if (l != 2 && l != 0) {
            this.a(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
        }
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k) {
        return m_(iblockaccess.getData(i, j, k));
    }

    public int d() {
        return 21;
    }

    public void postPlace(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemstack) {
        int l = (MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;

        world.setData(i, j, k, l, 2);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        int i1 = world.getData(i, j, k);

        if (m_(i1)) {
            world.setData(i, j, k, i1 & -5, 2);
        } else {
            int j1 = (MathHelper.floor((double) (entityhuman.yaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;
            int k1 = j(i1);

            if (k1 == (j1 + 2) % 4) {
                i1 = j1;
            }

            world.setData(i, j, k, i1 | 4, 2);
        }

        world.a(entityhuman, 1003, i, j, k, 0);
        return true;
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (!world.isStatic) {
            int i1 = world.getData(i, j, k);
            boolean flag = world.isBlockIndirectlyPowered(i, j, k);

            if (flag || l > 0 && Block.byId[l].isPowerSource()) {
                if (flag && !m_(i1)) {
                    world.setData(i, j, k, i1 | 4, 2);
                    world.a((EntityHuman) null, 1003, i, j, k, 0);
                } else if (!flag && m_(i1)) {
                    world.setData(i, j, k, i1 & -5, 2);
                    world.a((EntityHuman) null, 1003, i, j, k, 0);
                }
            }
        }
    }

    public static boolean m_(int i) {
        return (i & 4) != 0;
    }
}

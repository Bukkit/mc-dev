package net.minecraft.server;

public class BlockFence extends Block {

    public BlockFence(int i, int j) {
        super(i, j, Material.WOOD);
    }

    public BlockFence(int i, int j, Material material) {
        super(i, j, material);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return super.canPlace(world, i, j, k);
    }

    public AxisAlignedBB e(World world, int i, int j, int k) {
        boolean flag = this.b(world, i, j, k - 1);
        boolean flag1 = this.b(world, i, j, k + 1);
        boolean flag2 = this.b(world, i - 1, j, k);
        boolean flag3 = this.b(world, i + 1, j, k);
        float f = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.375F;
        float f3 = 0.625F;

        if (flag) {
            f2 = 0.0F;
        }

        if (flag1) {
            f3 = 1.0F;
        }

        if (flag2) {
            f = 0.0F;
        }

        if (flag3) {
            f1 = 1.0F;
        }

        return AxisAlignedBB.b((double) ((float) i + f), (double) j, (double) ((float) k + f2), (double) ((float) i + f1), (double) ((float) j + 1.5F), (double) ((float) k + f3));
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        boolean flag = this.b(iblockaccess, i, j, k - 1);
        boolean flag1 = this.b(iblockaccess, i, j, k + 1);
        boolean flag2 = this.b(iblockaccess, i - 1, j, k);
        boolean flag3 = this.b(iblockaccess, i + 1, j, k);
        float f = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.375F;
        float f3 = 0.625F;

        if (flag) {
            f2 = 0.0F;
        }

        if (flag1) {
            f3 = 1.0F;
        }

        if (flag2) {
            f = 0.0F;
        }

        if (flag3) {
            f1 = 1.0F;
        }

        this.a(f, 0.0F, f2, f1, 1.0F, f3);
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int c() {
        return 11;
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.getTypeId(i, j, k);

        if (l != this.id && l != Block.FENCE_GATE.id) {
            Block block = Block.byId[l];

            return block != null && block.material.j() && block.b() ? block.material != Material.PUMPKIN : false;
        } else {
            return true;
        }
    }
}

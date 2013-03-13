package net.minecraft.server;

public class BlockCobbleWall extends Block {

    public static final String[] a = new String[] { "normal", "mossy"};

    public BlockCobbleWall(int i, Block block) {
        super(i, block.material);
        this.c(block.strength);
        this.b(block.durability / 3.0F);
        this.a(block.stepSound);
        this.a(CreativeModeTab.b);
    }

    public int d() {
        return 32;
    }

    public boolean b() {
        return false;
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k) {
        return false;
    }

    public boolean c() {
        return false;
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        boolean flag = this.d(iblockaccess, i, j, k - 1);
        boolean flag1 = this.d(iblockaccess, i, j, k + 1);
        boolean flag2 = this.d(iblockaccess, i - 1, j, k);
        boolean flag3 = this.d(iblockaccess, i + 1, j, k);
        float f = 0.25F;
        float f1 = 0.75F;
        float f2 = 0.25F;
        float f3 = 0.75F;
        float f4 = 1.0F;

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

        if (flag && flag1 && !flag2 && !flag3) {
            f4 = 0.8125F;
            f = 0.3125F;
            f1 = 0.6875F;
        } else if (!flag && !flag1 && flag2 && flag3) {
            f4 = 0.8125F;
            f2 = 0.3125F;
            f3 = 0.6875F;
        }

        this.a(f, 0.0F, f2, f1, f4, f3);
    }

    public AxisAlignedBB b(World world, int i, int j, int k) {
        this.updateShape(world, i, j, k);
        this.maxY = 1.5D;
        return super.b(world, i, j, k);
    }

    public boolean d(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.getTypeId(i, j, k);

        if (l != this.id && l != Block.FENCE_GATE.id) {
            Block block = Block.byId[l];

            return block != null && block.material.k() && block.b() ? block.material != Material.PUMPKIN : false;
        } else {
            return true;
        }
    }

    public int getDropData(int i) {
        return i;
    }
}

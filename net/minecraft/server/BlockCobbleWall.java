package net.minecraft.server;

public class BlockCobbleWall extends Block {

    public static final String[] a = new String[] { "normal", "mossy"};

    public BlockCobbleWall(Block block) {
        super(block.material);
        this.c(block.strength);
        this.b(block.durability / 3.0F);
        this.a(block.stepSound);
        this.a(CreativeModeTab.b);
    }

    public int b() {
        return 32;
    }

    public boolean d() {
        return false;
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k) {
        return false;
    }

    public boolean c() {
        return false;
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        boolean flag = this.e(iblockaccess, i, j, k - 1);
        boolean flag1 = this.e(iblockaccess, i, j, k + 1);
        boolean flag2 = this.e(iblockaccess, i - 1, j, k);
        boolean flag3 = this.e(iblockaccess, i + 1, j, k);
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

    public AxisAlignedBB a(World world, int i, int j, int k) {
        this.updateShape(world, i, j, k);
        this.maxY = 1.5D;
        return super.a(world, i, j, k);
    }

    public boolean e(IBlockAccess iblockaccess, int i, int j, int k) {
        Block block = iblockaccess.getType(i, j, k);

        return block != this && block != Blocks.FENCE_GATE ? (block.material.k() && block.d() ? block.material != Material.PUMPKIN : false) : true;
    }

    public int getDropData(int i) {
        return i;
    }
}

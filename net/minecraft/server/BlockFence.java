package net.minecraft.server;

import java.util.List;

public class BlockFence extends Block {

    private final String a;

    public BlockFence(String s, Material material) {
        super(material);
        this.a = s;
        this.a(CreativeModeTab.c);
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        boolean flag = this.e(world, i, j, k - 1);
        boolean flag1 = this.e(world, i, j, k + 1);
        boolean flag2 = this.e(world, i - 1, j, k);
        boolean flag3 = this.e(world, i + 1, j, k);
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

        if (flag || flag1) {
            this.a(f, 0.0F, f2, f1, 1.5F, f3);
            super.a(world, i, j, k, axisalignedbb, list, entity);
        }

        f2 = 0.375F;
        f3 = 0.625F;
        if (flag2) {
            f = 0.0F;
        }

        if (flag3) {
            f1 = 1.0F;
        }

        if (flag2 || flag3 || !flag && !flag1) {
            this.a(f, 0.0F, f2, f1, 1.5F, f3);
            super.a(world, i, j, k, axisalignedbb, list, entity);
        }

        if (flag) {
            f2 = 0.0F;
        }

        if (flag1) {
            f3 = 1.0F;
        }

        this.a(f, 0.0F, f2, f1, 1.0F, f3);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        boolean flag = this.e(iblockaccess, i, j, k - 1);
        boolean flag1 = this.e(iblockaccess, i, j, k + 1);
        boolean flag2 = this.e(iblockaccess, i - 1, j, k);
        boolean flag3 = this.e(iblockaccess, i + 1, j, k);
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

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public boolean b(IBlockAccess iblockaccess, int i, int j, int k) {
        return false;
    }

    public int b() {
        return 11;
    }

    public boolean e(IBlockAccess iblockaccess, int i, int j, int k) {
        Block block = iblockaccess.getType(i, j, k);

        return block != this && block != Blocks.FENCE_GATE ? (block.material.k() && block.d() ? block.material != Material.PUMPKIN : false) : true;
    }

    public static boolean a(Block block) {
        return block == Blocks.FENCE || block == Blocks.NETHER_FENCE;
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        return world.isStatic ? true : ItemLeash.a(entityhuman, world, i, j, k);
    }
}

package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockThinFence extends Block {

    private final String a;
    private final boolean b;
    private final String c;

    protected BlockThinFence(int i, String s, String s1, Material material, boolean flag) {
        super(i, material);
        this.a = s1;
        this.b = flag;
        this.c = s;
        this.a(CreativeModeTab.c);
    }

    public int getDropType(int i, Random random, int j) {
        return !this.b ? 0 : super.getDropType(i, random, j);
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int d() {
        return 18;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        boolean flag = this.d(world.getTypeId(i, j, k - 1));
        boolean flag1 = this.d(world.getTypeId(i, j, k + 1));
        boolean flag2 = this.d(world.getTypeId(i - 1, j, k));
        boolean flag3 = this.d(world.getTypeId(i + 1, j, k));

        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1)) {
            if (flag2 && !flag3) {
                this.a(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
                super.a(world, i, j, k, axisalignedbb, list, entity);
            } else if (!flag2 && flag3) {
                this.a(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
                super.a(world, i, j, k, axisalignedbb, list, entity);
            }
        } else {
            this.a(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
        }

        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1)) {
            if (flag && !flag1) {
                this.a(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
                super.a(world, i, j, k, axisalignedbb, list, entity);
            } else if (!flag && flag1) {
                this.a(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
                super.a(world, i, j, k, axisalignedbb, list, entity);
            }
        } else {
            this.a(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
        }
    }

    public void g() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        float f = 0.4375F;
        float f1 = 0.5625F;
        float f2 = 0.4375F;
        float f3 = 0.5625F;
        boolean flag = this.d(iblockaccess.getTypeId(i, j, k - 1));
        boolean flag1 = this.d(iblockaccess.getTypeId(i, j, k + 1));
        boolean flag2 = this.d(iblockaccess.getTypeId(i - 1, j, k));
        boolean flag3 = this.d(iblockaccess.getTypeId(i + 1, j, k));

        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1)) {
            if (flag2 && !flag3) {
                f = 0.0F;
            } else if (!flag2 && flag3) {
                f1 = 1.0F;
            }
        } else {
            f = 0.0F;
            f1 = 1.0F;
        }

        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1)) {
            if (flag && !flag1) {
                f2 = 0.0F;
            } else if (!flag && flag1) {
                f3 = 1.0F;
            }
        } else {
            f2 = 0.0F;
            f3 = 1.0F;
        }

        this.a(f, 0.0F, f2, f1, 1.0F, f3);
    }

    public final boolean d(int i) {
        return Block.t[i] || i == this.id || i == Block.GLASS.id;
    }

    protected boolean r_() {
        return true;
    }

    protected ItemStack d_(int i) {
        return new ItemStack(this.id, 1, i);
    }
}

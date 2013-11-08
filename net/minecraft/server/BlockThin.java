package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockThin extends Block {

    private final String a;
    private final boolean b;
    private final String M;

    protected BlockThin(String s, String s1, Material material, boolean flag) {
        super(material);
        this.a = s1;
        this.b = flag;
        this.M = s;
        this.a(CreativeModeTab.c);
    }

    public Item getDropType(int i, Random random, int j) {
        return !this.b ? null : super.getDropType(i, random, j);
    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public int b() {
        return this.material == Material.SHATTERABLE ? 41 : 18;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        boolean flag = this.a(world.getType(i, j, k - 1));
        boolean flag1 = this.a(world.getType(i, j, k + 1));
        boolean flag2 = this.a(world.getType(i - 1, j, k));
        boolean flag3 = this.a(world.getType(i + 1, j, k));

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
        boolean flag = this.a(iblockaccess.getType(i, j, k - 1));
        boolean flag1 = this.a(iblockaccess.getType(i, j, k + 1));
        boolean flag2 = this.a(iblockaccess.getType(i - 1, j, k));
        boolean flag3 = this.a(iblockaccess.getType(i + 1, j, k));

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

    public final boolean a(Block block) {
        return block.j() || block == this || block == Blocks.GLASS || block == Blocks.STAINED_GLASS || block == Blocks.STAINED_GLASS_PANE || block instanceof BlockThin;
    }

    protected boolean E() {
        return true;
    }

    protected ItemStack j(int i) {
        return new ItemStack(Item.getItemOf(this), 1, i);
    }
}

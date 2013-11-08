package net.minecraft.server;

import java.util.List;
import java.util.Random;

public abstract class BlockStepAbstract extends Block {

    protected final boolean a;

    public BlockStepAbstract(boolean flag, Material material) {
        super(material);
        this.a = flag;
        if (flag) {
            this.q = true;
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }

        this.g(255);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        if (this.a) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
            boolean flag = (iblockaccess.getData(i, j, k) & 8) != 0;

            if (flag) {
                this.a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            }
        }
    }

    public void g() {
        if (this.a) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        this.updateShape(world, i, j, k);
        super.a(world, i, j, k, axisalignedbb, list, entity);
    }

    public boolean c() {
        return this.a;
    }

    public int getPlacedData(World world, int i, int j, int k, int l, float f, float f1, float f2, int i1) {
        return this.a ? i1 : (l != 0 && (l == 1 || (double) f1 <= 0.5D) ? i1 : i1 | 8);
    }

    public int a(Random random) {
        return this.a ? 2 : 1;
    }

    public int getDropData(int i) {
        return i & 7;
    }

    public boolean d() {
        return this.a;
    }

    public abstract String b(int i);

    public int getDropData(World world, int i, int j, int k) {
        return super.getDropData(world, i, j, k) & 7;
    }
}

package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class BlockStep extends Block {

    public static final String[] a = new String[] { "stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick"};
    private boolean b;

    public BlockStep(int i, boolean flag) {
        super(i, 6, Material.STONE);
        this.b = flag;
        if (!flag) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        } else {
            n[i] = true;
        }

        this.f(255);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        if (this.b) {
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

    public void f() {
        if (this.b) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist) {
        this.updateShape(world, i, j, k);
        super.a(world, i, j, k, axisalignedbb, arraylist);
    }

    public int a(int i, int j) {
        int k = j & 7;

        return k == 0 ? (i <= 1 ? 6 : 5) : (k == 1 ? (i == 0 ? 208 : (i == 1 ? 176 : 192)) : (k == 2 ? 4 : (k == 3 ? 16 : (k == 4 ? Block.BRICK.textureId : (k == 5 ? Block.SMOOTH_BRICK.textureId : 6)))));
    }

    public int a(int i) {
        return this.a(i, 0);
    }

    public boolean a() {
        return this.b;
    }

    public void postPlace(World world, int i, int j, int k, int l) {
        if (l == 0 && !this.b) {
            int i1 = world.getData(i, j, k) & 7;

            world.setData(i, j, k, i1 | 8);
        }
    }

    public int getDropType(int i, Random random, int j) {
        return Block.STEP.id;
    }

    public int a(Random random) {
        return this.b ? 2 : 1;
    }

    protected int getDropData(int i) {
        return i & 7;
    }

    public boolean b() {
        return this.b;
    }

    protected ItemStack a_(int i) {
        return new ItemStack(Block.STEP.id, 1, i & 7);
    }
}

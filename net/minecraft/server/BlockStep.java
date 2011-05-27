package net.minecraft.server;

import java.util.Random;

public class BlockStep extends Block {

    private boolean a;

    public BlockStep(int i, boolean flag) {
        super(i, 6, Material.STONE);
        this.a = flag;
        if (!flag) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }

        this.e(255);
    }

    public int a(int i) {
        return i <= 1 ? 6 : 5;
    }

    public boolean a() {
        return this.a;
    }

    public void b(World world, int i, int j, int k, int l) {
        if (this == Block.STEP) {
            ;
        }
    }

    public void e(World world, int i, int j, int k) {
        if (this != Block.STEP) {
            super.e(world, i, j, k);
        }

        int l = world.getTypeId(i, j - 1, k);

        if (l == STEP.id) {
            world.e(i, j, k, 0);
            world.e(i, j - 1, k, Block.DOUBLE_STEP.id);
        }
    }

    public int a(int i, Random random) {
        return Block.STEP.id;
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        if (this != Block.STEP) {
            super.a(iblockaccess, i, j, k, l);
        }

        return l == 1 ? true : (!super.a(iblockaccess, i, j, k, l) ? false : (l == 0 ? true : iblockaccess.getTypeId(i, j, k) != this.id));
    }
}

package net.minecraft.server;

import java.util.Random;

public class BlockMushroom extends BlockFlower {

    protected BlockMushroom(int i, int j) {
        super(i, j);
        float f = 0.2F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.a(true);
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (random.nextInt(100) == 0) {
            int l = i + random.nextInt(3) - random.nextInt(3);
            int i1 = j + random.nextInt(2) - random.nextInt(2);
            int j1 = k + random.nextInt(3) - random.nextInt(3);

            if (world.isEmpty(l, i1, j1) && this.f(world, l, i1, j1)) {
                world.setTypeId(l, i1, j1, this.id);
            }
        }
    }

    protected boolean c(int i) {
        return Block.o[i];
    }

    public boolean f(World world, int i, int j, int k) {
        return j >= 0 && j < 128 ? world.j(i, j, k) < 13 && this.c(world.getTypeId(i, j - 1, k)) : false;
    }
}

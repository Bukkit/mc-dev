package net.minecraft.server;

import java.util.Random;

public class BlockSapling extends BlockFlower {

    protected BlockSapling(int i, int j) {
        super(i, j);
        float f = 0.4F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (!world.isStatic) {
            super.a(world, i, j, k, random);
            if (world.getLightLevel(i, j + 1, k) >= 9 && random.nextInt(7) == 0) {
                int l = world.getData(i, j, k);

                if ((l & 8) == 0) {
                    world.setData(i, j, k, l | 8);
                } else {
                    this.grow(world, i, j, k, random);
                }
            }
        }
    }

    public int a(int i, int j) {
        j &= 3;
        return j == 1 ? 63 : (j == 2 ? 79 : super.a(i, j));
    }

    public void grow(World world, int i, int j, int k, Random random) {
        int l = world.getData(i, j, k) & 3;

        world.setRawTypeId(i, j, k, 0);
        Object object = null;

        if (l == 1) {
            object = new WorldGenTaiga2(true);
        } else if (l == 2) {
            object = new WorldGenForest(true);
        } else {
            object = new WorldGenTrees(true);
            if (random.nextInt(10) == 0) {
                object = new WorldGenBigTree(true);
            }
        }

        if (!((WorldGenerator) object).a(world, random, i, j, k)) {
            world.setRawTypeIdAndData(i, j, k, this.id, l);
        }
    }

    protected int getDropData(int i) {
        return i & 3;
    }
}

package net.minecraft.server;

import java.util.Random;

public class BlockLongGrass extends BlockFlower {

    protected BlockLongGrass(int i, int j) {
        super(i, j);
        float f = 0.4F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    public int a(int i, Random random) {
        return random.nextInt(8) == 0 ? Item.SEEDS.id : -1;
    }
}

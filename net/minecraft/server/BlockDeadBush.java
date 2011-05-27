package net.minecraft.server;

import java.util.Random;

public class BlockDeadBush extends BlockFlower {

    protected BlockDeadBush(int i, int j) {
        super(i, j);
        float f = 0.4F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    protected boolean c(int i) {
        return i == Block.SAND.id;
    }

    public int a(int i, int j) {
        return this.textureId;
    }

    public int a(int i, Random random) {
        return -1;
    }
}

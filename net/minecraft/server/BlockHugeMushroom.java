package net.minecraft.server;

import java.util.Random;

public class BlockHugeMushroom extends Block {

    private int a;

    public BlockHugeMushroom(int i, Material material, int j, int k) {
        super(i, j, material);
        this.a = k;
    }

    public int a(int i, int j) {
        return j == 10 && i > 1 ? this.textureId - 1 : (j >= 1 && j <= 9 && i == 1 ? this.textureId - 16 - this.a : (j >= 1 && j <= 3 && i == 2 ? this.textureId - 16 - this.a : (j >= 7 && j <= 9 && i == 3 ? this.textureId - 16 - this.a : ((j == 1 || j == 4 || j == 7) && i == 4 ? this.textureId - 16 - this.a : ((j == 3 || j == 6 || j == 9) && i == 5 ? this.textureId - 16 - this.a : (j == 14 ? this.textureId - 16 - this.a : (j == 15 ? this.textureId - 1 : this.textureId)))))));
    }

    public int a(Random random) {
        int i = random.nextInt(10) - 7;

        if (i < 0) {
            i = 0;
        }

        return i;
    }

    public int getDropType(int i, Random random, int j) {
        return Block.BROWN_MUSHROOM.id + this.a;
    }
}

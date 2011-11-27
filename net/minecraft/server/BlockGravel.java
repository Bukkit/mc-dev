package net.minecraft.server;

import java.util.Random;

public class BlockGravel extends BlockSand {

    public BlockGravel(int i, int j) {
        super(i, j);
    }

    public int getDropType(int i, Random random, int j) {
        return random.nextInt(10 - j * 3) == 0 ? Item.FLINT.id : this.id;
    }
}

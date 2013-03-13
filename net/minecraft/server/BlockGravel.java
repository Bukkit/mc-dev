package net.minecraft.server;

import java.util.Random;

public class BlockGravel extends BlockSand {

    public BlockGravel(int i) {
        super(i);
    }

    public int getDropType(int i, Random random, int j) {
        if (j > 3) {
            j = 3;
        }

        return random.nextInt(10 - j * 3) == 0 ? Item.FLINT.id : this.id;
    }
}

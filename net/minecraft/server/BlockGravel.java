package net.minecraft.server;

import java.util.Random;

public class BlockGravel extends BlockFalling {

    public BlockGravel() {}

    public Item getDropType(int i, Random random, int j) {
        if (j > 3) {
            j = 3;
        }

        return random.nextInt(10 - j * 3) == 0 ? Items.FLINT : Item.getItemOf(this);
    }
}

package net.minecraft.server;

import java.util.Random;

public class BlockHugeMushroom extends Block {

    private static final String[] a = new String[] { "mushroom_skin_brown", "mushroom_skin_red"};
    private final int b;

    public BlockHugeMushroom(int i, Material material, int j) {
        super(i, material);
        this.b = j;
    }

    public int a(Random random) {
        int i = random.nextInt(10) - 7;

        if (i < 0) {
            i = 0;
        }

        return i;
    }

    public int getDropType(int i, Random random, int j) {
        return Block.BROWN_MUSHROOM.id + this.b;
    }
}

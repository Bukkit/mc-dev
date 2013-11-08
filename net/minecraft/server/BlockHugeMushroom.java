package net.minecraft.server;

import java.util.Random;

public class BlockHugeMushroom extends Block {

    private static final String[] a = new String[] { "skin_brown", "skin_red"};
    private final int b;

    public BlockHugeMushroom(Material material, int i) {
        super(material);
        this.b = i;
    }

    public int a(Random random) {
        int i = random.nextInt(10) - 7;

        if (i < 0) {
            i = 0;
        }

        return i;
    }

    public Item getDropType(int i, Random random, int j) {
        return Item.d(Block.b((Block) Blocks.BROWN_MUSHROOM) + this.b);
    }
}

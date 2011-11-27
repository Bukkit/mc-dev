package net.minecraft.server;

import java.util.Random;

public class BlockLightStone extends Block {

    public BlockLightStone(int i, int j, Material material) {
        super(i, j, material);
    }

    public int getDropCount(int i, Random random) {
        return MathHelper.a(this.a(random) + random.nextInt(i + 1), 1, 4);
    }

    public int a(Random random) {
        return 2 + random.nextInt(3);
    }

    public int getDropType(int i, Random random, int j) {
        return Item.GLOWSTONE_DUST.id;
    }
}

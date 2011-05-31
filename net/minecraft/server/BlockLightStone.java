package net.minecraft.server;

import java.util.Random;

public class BlockLightStone extends Block {

    public BlockLightStone(int i, int j, Material material) {
        super(i, j, material);
    }

    public int a(Random random) {
        return 2 + random.nextInt(3);
    }

    public int a(int i, Random random) {
        return Item.GLOWSTONE_DUST.id;
    }
}

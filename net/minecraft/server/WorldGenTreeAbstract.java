package net.minecraft.server;

import java.util.Random;

public abstract class WorldGenTreeAbstract extends WorldGenerator {

    public WorldGenTreeAbstract(boolean flag) {
        super(flag);
    }

    protected boolean a(Block block) {
        return block.getMaterial() == Material.AIR || block.getMaterial() == Material.LEAVES || block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.LOG || block == Blocks.LOG2 || block == Blocks.SAPLING || block == Blocks.VINE;
    }

    public void b(World world, Random random, int i, int j, int k) {}
}

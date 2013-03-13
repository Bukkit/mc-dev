package net.minecraft.server;

import java.util.Random;

public class BlockMonsterEggs extends Block {

    public static final String[] a = new String[] { "stone", "cobble", "brick"};

    public BlockMonsterEggs(int i) {
        super(i, Material.CLAY);
        this.c(0.0F);
        this.a(CreativeModeTab.c);
    }

    public void postBreak(World world, int i, int j, int k, int l) {
        if (!world.isStatic) {
            EntitySilverfish entitysilverfish = new EntitySilverfish(world);

            entitysilverfish.setPositionRotation((double) i + 0.5D, (double) j, (double) k + 0.5D, 0.0F, 0.0F);
            world.addEntity(entitysilverfish);
            entitysilverfish.aU();
        }

        super.postBreak(world, i, j, k, l);
    }

    public int a(Random random) {
        return 0;
    }

    public static boolean d(int i) {
        return i == Block.STONE.id || i == Block.COBBLESTONE.id || i == Block.SMOOTH_BRICK.id;
    }

    public static int e(int i) {
        return i == Block.COBBLESTONE.id ? 1 : (i == Block.SMOOTH_BRICK.id ? 2 : 0);
    }

    protected ItemStack c_(int i) {
        Block block = Block.STONE;

        if (i == 1) {
            block = Block.COBBLESTONE;
        }

        if (i == 2) {
            block = Block.SMOOTH_BRICK;
        }

        return new ItemStack(block);
    }

    public int getDropData(World world, int i, int j, int k) {
        return world.getData(i, j, k);
    }
}

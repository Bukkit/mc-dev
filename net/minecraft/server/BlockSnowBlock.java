package net.minecraft.server;

import java.util.Random;

public class BlockSnowBlock extends Block {

    protected BlockSnowBlock() {
        super(Material.SNOW_BLOCK);
        this.a(true);
        this.a(CreativeModeTab.b);
    }

    public Item getDropType(int i, Random random, int j) {
        return Items.SNOW_BALL;
    }

    public int a(Random random) {
        return 4;
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (world.b(EnumSkyBlock.BLOCK, i, j, k) > 11) {
            this.b(world, i, j, k, world.getData(i, j, k), 0);
            world.setAir(i, j, k);
        }
    }
}

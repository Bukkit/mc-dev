package net.minecraft.server;

import java.util.Random;

public class BlockLockedChest extends Block {

    protected BlockLockedChest(int i) {
        super(i, Material.WOOD);
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return true;
    }

    public void a(World world, int i, int j, int k, Random random) {
        world.setAir(i, j, k);
    }
}

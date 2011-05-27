package net.minecraft.server;

import java.util.Random;

public class BlockMobSpawner extends BlockContainer {

    protected BlockMobSpawner(int i, int j) {
        super(i, j, Material.STONE);
    }

    protected TileEntity a_() {
        return new TileEntityMobSpawner();
    }

    public int a(int i, Random random) {
        return 0;
    }

    public int a(Random random) {
        return 0;
    }

    public boolean a() {
        return false;
    }
}

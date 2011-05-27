package net.minecraft.server;

import java.util.Random;

public class BlockLockedChest extends Block {

    protected BlockLockedChest(int i) {
        super(i, Material.WOOD);
        this.textureId = 26;
    }

    public int a(int i) {
        return i == 1 ? this.textureId - 1 : (i == 0 ? this.textureId - 1 : (i == 3 ? this.textureId + 1 : this.textureId));
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return true;
    }

    public void a(World world, int i, int j, int k, Random random) {
        world.setTypeId(i, j, k, 0);
    }
}

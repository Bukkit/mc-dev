package net.minecraft.server;

public abstract class BlockDirectional extends Block {

    protected BlockDirectional(int i, int j, Material material) {
        super(i, j, material);
    }

    protected BlockDirectional(int i, Material material) {
        super(i, material);
    }

    public static int e(int i) {
        return i & 3;
    }
}

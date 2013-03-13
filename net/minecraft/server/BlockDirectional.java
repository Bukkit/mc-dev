package net.minecraft.server;

public abstract class BlockDirectional extends Block {

    protected BlockDirectional(int i, Material material) {
        super(i, material);
    }

    public static int j(int i) {
        return i & 3;
    }
}

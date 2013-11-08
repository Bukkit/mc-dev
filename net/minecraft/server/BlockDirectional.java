package net.minecraft.server;

public abstract class BlockDirectional extends Block {

    protected BlockDirectional(Material material) {
        super(material);
    }

    public static int l(int i) {
        return i & 3;
    }
}

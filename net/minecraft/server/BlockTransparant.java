package net.minecraft.server;

public class BlockTransparant extends Block {

    protected boolean c;

    protected BlockTransparant(int i, int j, Material material, boolean flag) {
        super(i, j, material);
        this.c = flag;
    }

    public boolean d() {
        return false;
    }
}

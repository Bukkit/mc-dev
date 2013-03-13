package net.minecraft.server;

public class BlockTransparant extends Block {

    protected boolean d;

    protected BlockTransparant(int i, Material material, boolean flag) {
        super(i, material);
        this.d = flag;
    }

    public boolean c() {
        return false;
    }
}

package net.minecraft.server;

public class BlockHalfTransparant extends Block {

    private boolean a;

    protected BlockHalfTransparant(int i, int j, Material material, boolean flag) {
        super(i, j, material);
        this.a = flag;
    }

    public boolean a() {
        return false;
    }
}

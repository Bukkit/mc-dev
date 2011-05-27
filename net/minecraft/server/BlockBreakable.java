package net.minecraft.server;

public class BlockBreakable extends Block {

    private boolean a;

    protected BlockBreakable(int i, int j, Material material, boolean flag) {
        super(i, j, material);
        this.a = flag;
    }

    public boolean a() {
        return false;
    }
}

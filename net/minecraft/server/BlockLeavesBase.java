package net.minecraft.server;

public class BlockLeavesBase extends Block {

    protected boolean b;

    protected BlockLeavesBase(int i, int j, Material material, boolean flag) {
        super(i, j, material);
        this.b = flag;
    }

    public boolean a() {
        return false;
    }
}

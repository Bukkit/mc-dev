package net.minecraft.server;

public class BlockLeavesBase extends Block {

    protected boolean a;

    protected BlockLeavesBase(int i, int j, Material material, boolean flag) {
        super(i, j, material);
        this.a = flag;
    }

    public boolean a() {
        return false;
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        int i1 = iblockaccess.getTypeId(i, j, k);

        return !this.a && i1 == this.id ? false : super.a(iblockaccess, i, j, k, l);
    }
}

package net.minecraft.server;

public class BlockBreakable extends Block {

    private boolean a;

    protected BlockBreakable(int i, int j, Material material, boolean flag) {
        super(i, j, material);
        this.a = flag;
    }

    public boolean b() {
        return false;
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        int i1 = iblockaccess.a(i, j, k);

        return !this.a && i1 == this.bc ? false : super.a(iblockaccess, i, j, k, l);
    }
}

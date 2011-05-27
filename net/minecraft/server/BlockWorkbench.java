package net.minecraft.server;

public class BlockWorkbench extends Block {

    protected BlockWorkbench(int i) {
        super(i, Material.c);
        this.bh = 59;
    }

    public int a(int i) {
        return i == 1 ? this.bh - 16 : (i == 0 ? Block.WOOD.a(0) : (i != 2 && i != 4 ? this.bh : this.bh + 1));
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        entityhuman.F();
        return true;
    }
}

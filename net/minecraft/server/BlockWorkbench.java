package net.minecraft.server;

public class BlockWorkbench extends Block {

    protected BlockWorkbench(int i) {
        super(i, Material.c);
        this.bb = 59;
    }

    public int a(int i) {
        return i == 1 ? this.bb - 16 : (i == 0 ? Block.WOOD.a(0) : (i != 2 && i != 4 ? this.bb : this.bb + 1));
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        entityhuman.A();
        return true;
    }
}

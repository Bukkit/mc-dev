package net.minecraft.server;

public class BlockWorkbench extends Block {

    protected BlockWorkbench(int i) {
        super(i, Material.c);
        this.bg = 59;
    }

    public int a(int i) {
        return i == 1 ? this.bg - 16 : (i == 0 ? Block.WOOD.a(0) : (i != 2 && i != 4 ? this.bg : this.bg + 1));
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        if (world.z) {
            return true;
        } else {
            entityhuman.a(i, j, k);
            return true;
        }
    }
}

package net.minecraft.server;

public class BlockWorkbench extends Block {

    protected BlockWorkbench(int i) {
        super(i, Material.WOOD);
        this.textureId = 59;
    }

    public int a(int i) {
        return i == 1 ? this.textureId - 16 : (i == 0 ? Block.WOOD.a(0) : (i != 2 && i != 4 ? this.textureId : this.textureId + 1));
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
        if (world.isStatic) {
            return true;
        } else {
            entityhuman.b(i, j, k);
            return true;
        }
    }
}

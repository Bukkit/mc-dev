package net.minecraft.server;

public class BlockWorkbench extends Block {

    protected BlockWorkbench() {
        super(Material.WOOD);
        this.a(CreativeModeTab.c);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            entityhuman.startCrafting(i, j, k);
            return true;
        }
    }
}

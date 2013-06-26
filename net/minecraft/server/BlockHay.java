package net.minecraft.server;

public class BlockHay extends BlockRotatable {

    public BlockHay(int i) {
        super(i, Material.GRASS);
        this.a(CreativeModeTab.b);
    }

    public int d() {
        return 31;
    }
}

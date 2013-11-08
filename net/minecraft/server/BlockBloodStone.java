package net.minecraft.server;

public class BlockBloodStone extends Block {

    public BlockBloodStone() {
        super(Material.STONE);
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor f(int i) {
        return MaterialMapColor.K;
    }
}

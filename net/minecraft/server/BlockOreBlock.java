package net.minecraft.server;

public class BlockOreBlock extends Block {

    private final MaterialMapColor a;

    public BlockOreBlock(MaterialMapColor materialmapcolor) {
        super(Material.ORE);
        this.a = materialmapcolor;
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor f(int i) {
        return this.a;
    }
}
